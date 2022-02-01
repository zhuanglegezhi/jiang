# 源码

## 1. 创建CompletableFuture

创建的方法有很多，甚至可以直接new一个。我们来看一下supplyAsync异步创建的方法。

```java
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
    return asyncSupplyStage(screenExecutor(executor), supplier);
}

static Executor screenExecutor(Executor e) {
    if (!useCommonPool && e == ForkJoinPool.commonPool())
        return asyncPool;
    if (e == null) throw new NullPointerException();
    return e;
}
```

入参Supplier，带返回值的函数。如果是异步方法，并且传递了执行器，那么会使用传入的执行器去执行任务。否则采用公共的ForkJoin并行线程池，如果不支持并行，新建一个线程去执行。

这里我们需要注意ForkJoin是通过守护线程去执行任务的。所以必须有非守护线程的存在才行。



## 2. CompletableFuture#asyncSupplyStage方法

```java
static <U> CompletableFuture<U> asyncSupplyStage(Executor e, Supplier<U> f) {
    if (f == null) throw new NullPointerException();
    CompletableFuture<U> d = new CompletableFuture<U>();
    e.execute(new AsyncSupply<U>(d, f));
    return d;
}
```

这里会创建一个用于返回的CompletableFuture。

然后构造一个AsyncSupply，并将创建的CompletableFuture作为构造参数传入。

那么，任务的执行完全依赖AsyncSupply。



## 3. AsyncSupply#run

```java
public void run() {
    CompletableFuture<T> d; Supplier<T> f;
    if ((d = dep) != null && (f = fn) != null) {
        dep = null; fn = null;
        if (d.result == null) {
            try {
                d.completeValue(f.get());
            } catch (Throwable ex) {
                d.completeThrowable(ex);
            }
        }
        d.postComplete();
    }
}
```

1. 该方法会调用Supplier的get方法。并将结果设置到CompletableFuture中。我们应该清楚这些操作都是在异步线程中调用的。
2. `d.postComplete`方法就是通知任务执行完成。触发后续依赖任务的执行，也就是实现CompletionStage的关键点。

在看postComplete方法之前我们先来看一下创建依赖任务的逻辑。



## 4. CompletableFuture#thenAcceptAsync方法

```java
public c<Void> thenAcceptAsync(Consumer<? super T> action) {
    return uniAcceptStage(asyncPool, action);
}

private CompletableFuture<Void> uniAcceptStage(Executor e, Consumer<? super T> f) {
    if (f == null) throw new NullPointerException();
    CompletableFuture<Void> d = new CompletableFuture<Void>();
    if (e != null || !d.uniAccept(this, f, null)) {
        # 1
        UniAccept<T> c = new UniAccept<T>(e, d, this, f);
        push(c);
        c.tryFire(SYNC);
    }
    return d;
}
```

上面提到过。thenAcceptAsync是用来消费CompletableFuture的。该方法调用uniAcceptStage。

**uniAcceptStage逻辑：**

1. 构造一个CompletableFuture，主要是为了链式调用。
2. 如果为异步任务，直接返回。因为源任务结束后会触发异步线程执行对应逻辑。
3. 如果为同步任务（e==null），会调用d.uniAccept方法。这个方法在这里逻辑：如果源任务完成，调用f，返回true。否则进入if代码块（Mark 1）。
4. 如果是异步任务直接进入if（Mark 1）。

**Mark1逻辑：**

1. 构造一个UniAccept，将其push入栈。这里通过CAS实现乐观锁实现。
2. 调用c.tryFire方法。

```java
final CompletableFuture<Void> tryFire(int mode) {
    CompletableFuture<Void> d; CompletableFuture<T> a;
    if ((d = dep) == null ||
        !d.uniAccept(a = src, fn, mode > 0 ? null : this))
        return null;
    dep = null; src = null; fn = null;
    return d.postFire(a, mode);
}
```

1. 会调用d.uniAccept方法。其实该方法判断源任务是否完成，如果完成则执行依赖任务，否则返回false。
2. 如果依赖任务已经执行，调用d.postFire，主要就是Fire的后续处理。根据不同模式逻辑不同。

这里简单说一下，其实mode有同步异步，和迭代。迭代为了避免无限递归。

**这里强调一下d.uniAccept方法的第三个参数。**

如果是异步调用（mode>0），传入null。否则传入this。

区别看下面代码。c不为null会调用c.claim方法。

```java
try {
    if (c != null && !c.claim())
        return false;
    @SuppressWarnings("unchecked") S s = (S) r;
    f.accept(s);
    completeNull();
} catch (Throwable ex) {
    completeThrowable(ex);
}

final boolean claim() {
    Executor e = executor;
    if (compareAndSetForkJoinTaskTag((short)0, (short)1)) {
        if (e == null)
            return true;
        executor = null; // disable
        e.execute(this);
    }
    return false;
}
```

**claim方法是逻辑：**

- 如果异步线程为null。说明同步，那么直接返回true。最后上层函数会调用f.accept(s)同步执行任务。
- 如果异步线程不为null，那么使用异步线程去执行this。

this的run任务如下。也就是在异步线程同步调用tryFire方法。达到其被异步线程执行的目的。

```
public final void run(){ 
   tryFire(ASYNC); 
}
```

看完上面的逻辑，我们基本理解依赖任务的逻辑。

其实就是先判断源任务是否完成，如果完成，直接在对应线程执行以来任务（如果是同步，则在当前线程处理，否则在异步线程处理）

如果任务没有完成，直接返回，因为等任务完成之后会通过postComplete去触发调用依赖任务。

## 5. postComplete方法

```java
final void postComplete() {
    /*
     * On each step, variable f holds current dependents to pop
     * and run.  It is extended along only one path at a time,
     * pushing others to avoid unbounded recursion.
     */
    CompletableFuture<?> f = this; Completion h;
    while ((h = f.stack) != null ||
           (f != this && (h = (f = this).stack) != null)) {
        CompletableFuture<?> d; Completion t;
        if (f.casStack(h, t = h.next)) {
            if (t != null) {
                if (f != this) {
                    pushStack(h);
                    continue;
                }
                h.next = null;    // detach
            }
            f = (d = h.tryFire(NESTED)) == null ? this : d;
        }
    }
}
```

在源任务完成之后会调用。

其实逻辑很简单，就是迭代堆栈的依赖任务。调用h.tryFire方法。NESTED就是为了避免递归死循环。因为FirePost会调用postComplete。如果是NESTED，则不调用。

堆栈的内容其实就是在依赖任务创建的时候加入进去的。上面我们已经提到过。



## 4.总结

基本上述源码已经分析了逻辑。

因为涉及异步等操作，我们需要理一下（这里针对全异步任务）：

1. 创建CompletableFuture成功之后会通过异步线程去执行对应任务。
2. 如果CompletableFuture还有依赖任务（异步），会将任务加入到CompletableFuture的堆栈保存起来。以供后续完成后执行依赖任务。

> 当然，创建依赖任务并不只是将其加入堆栈。如果源任务在创建依赖任务的时候已经执行完成，那么当前线程会触发依赖任务的异步线程直接处理依赖任务。并且会告诉堆栈其他的依赖任务源任务已经完成。

主要是考虑代码的复用。所以逻辑相对难理解。

postComplete方法会被源任务线程执行完源任务后调用。同样也可能被依赖任务线程后调用。

执行依赖任务的方法主要就是靠tryFire方法。因为这个方法可能会被多种不同类型线程触发，所以逻辑也绕一点。（其他依赖任务线程、源任务线程、当前依赖任务线程）

- 如果是当前依赖任务线程，那么会执行依赖任务，并且会通知其他依赖任务。
- 如果是源任务线程，和其他依赖任务线程，则将任务转换给依赖线程去执行。不需要通知其他依赖任务，避免死递归。