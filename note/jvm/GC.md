# 一、基本概念



## 1. 可达性分析算法

- **基本思想**

>通过一些被称为引用链（GC Roots）的对象作为起点，从这些节点开始向下搜索，搜索走过的路径被称为（Reference Chain)，当一个对象到GC Roots没有任何引用链相连时（即从GC Roots节点到该节点不可达），则证明该对象是不可用的。

- **GC Roots**
  - 虚拟机栈（栈帧中的本地变量表）中引用的对象
  - 方法区中类静态属性引用的对象
  - 方法区中常量引用的对象
  - 本地方法栈中JNI（即一般说的Native方法）引用的对象



## 2. 引用

强引用（Strong Reference)、软引用（Soft Reference)、弱引用（Weak Reference)、虚引用（Phantom Reference)，这四种引用从上到下，依次减弱

### 2.1 强引用

> 强引用就是指在程序代码中普遍存在的，类似`Object obj = new Object()`这类似的引用，只要强引用在，垃圾搜集器永远不会搜集被引用的对象。也就是说，**宁愿出现内存溢出，也不会回收这些对象**。

### 2.2 软引用

> 软引用是用来描述一些有用但并不是必需的对象，在Java中用`java.lang.ref.SoftReference`类来表示。对于软引用关联着的对象，**只有在内存不足的时候JVM才会回收该对象**。因此，这一点可以很好地用来解决OOM的问题，并且这个特性很适合用来实现缓存：比如网页缓存、图片缓存等。

```java
 public class Main {
    public static void main(String[] args) {
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
    }
}
```

### 2.3 弱引用

> 弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，**无论内存是否充足，都会回收被弱引用关联的对象**。在java中，用java.lang.ref.WeakReference类来表示。

```java
public class Main {
    public static void main(String[] args) {
     
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
         
        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }
}
```

### 2.4 虚引用

> 虚引用和前面的软引用、弱引用不同，它并不影响对象的生命周期。在java中用`java.lang.ref.PhantomReference`类表示。如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收。
>
>  要注意的是，虚引用必须和引用队列关联使用，当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会把这个虚引用加入到与之 关联的引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。



```java
public class Main {
    public static void main(String[] args) {
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());
    }
}
```

### 2.5 软引用和弱引用进一步说明

在SoftReference类中，有三个方法，两个构造方法和一个get方法（WekReference类似）：

```dart
public class SoftReference<T> extends Reference<T> {

    /**
     * Timestamp clock, updated by the garbage collector
     */
    static private long clock;

    /**
     * Timestamp updated by each invocation of the get method.  The VM may use
     * this field when selecting soft references to be cleared, but it is not
     * required to do so.
     */
    private long timestamp;

    /**
     * Creates a new soft reference that refers to the given object.  The new
     * reference is not registered with any queue.
     *
     * @param referent object the new soft reference will refer to
     */
    public SoftReference(T referent) {
        super(referent);
        this.timestamp = clock;
    }

    /**
     * Creates a new soft reference that refers to the given object and is
     * registered with the given queue.
     *
     * @param referent object the new soft reference will refer to
     * @param q the queue with which the reference is to be registered,
     *          or <tt>null</tt> if registration is not required
     *
     */
    public SoftReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
        this.timestamp = clock;
    }

    /**
     * Returns this reference object's referent.  If this reference object has
     * been cleared, either by the program or by the garbage collector, then
     * this method returns <code>null</code>.
     *
     * @return   The object to which this reference refers, or
     *           <code>null</code> if this reference object has been cleared
     */
    public T get() {
        T o = super.get();
        if (o != null && this.timestamp != clock)
            this.timestamp = clock;
        return o;
    }

}
```

get方法用来获取与软引用关联的对象的引用，如果该对象被回收了，则返回null。

在使用软引用和弱引用的时候，我们可以显示地通过System.gc()来通知JVM进行垃圾回收，但是要注意的是，虽然发出了通知，JVM不一定会立刻执行，也就是说这句是无法确保此时JVM一定会进行垃圾回收的。

### 2.6 虚引用进一步说明：

虚引用中有一个构造函数，可以看出，其必须和一个引用队列一起存在。get()方法永远返回null，因为虚引用永远不可达。

```java
public class PhantomReference<T> extends Reference<T> {

    /**
     * Returns this reference object's referent.  Because the referent of a
     * phantom reference is always inaccessible, this method always returns
     * <code>null</code>.
     *
     * @return  <code>null</code>
     */
    public T get() {
        return null;
    }

    /**
     * Creates a new phantom reference that refers to the given object and
     * is registered with the given queue.
     *
     * <p> It is possible to create a phantom reference with a <tt>null</tt>
     * queue, but such a reference is completely useless: Its <tt>get</tt>
     * method will always return null and, since it does not have a queue, it
     * will never be enqueued.
     *
     * @param referent the object the new phantom reference will refer to
     * @param q the queue with which the reference is to be registered,
     *          or <tt>null</tt> if registration is not required
     */
    public PhantomReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }
} 
```



## 3. 安全点、安全区域






# 二、垃圾收集算法

## 1. 标记 - 清除

- 会产生内存碎片



## 2. 复制算法
- 空间会浪费，每次只能用一半的内存



## 3. 标记整理
- 整理过程会降低GC效率

  
## 4. 分代收集
- 新生代：复制算法（大部分对象都是朝生夕死，只要付出对少量存活对象的复制成本即可完成收集）
- 老年代：标记清理/标记整理



# 三、垃圾收集器
![截屏2021-11-17 02.18.55](/Users/zz/Library/Application Support/typora-user-images/截屏2021-11-17 02.18.55.png)				 			 			 		



## 三色标记算法

### 思想

三色标记法将对象的颜色分为了黑、灰、白，三种颜色。

**白色** ：该对象没有被标记过。（垃圾对象）

**灰色** ：该对象已经被标记过了，但该对象下的属性没有全被标记完。（GC需要从此对象中去寻找垃圾）

**黑色** ：该对象已经被标记过了，且该对象下的属性也全部都被标记过了。（程序所需要的对象）



### 流程

1. 首先创建三个集合：白、灰、黑。
2. 将所有对象放入白色集合中。
3. 然后从根节点开始遍历所有对象（注意这里并不**递归遍历** ），把遍历到的对象从白色集合放入灰色集合。
4. 之后遍历灰色集合，将灰色对象引用的对象从白色集合放入灰色集合，之后将此灰色对象放入黑色集合
5. 重复 4 直到灰色中无任何对象
6. 通过write-barrier检测对象有变化，重复以上操作
7. 收集所有白色对象（垃圾）




## 1. CMS

- 收集器是一种以获取最短回收停顿时间为目标的收集器
- 算法：标记—清除
- 默认情况下在老年代使用了68%及以上的内存的时候就开始CMS。
- 老年代垃圾收集器



![图片](https://mmbiz.qpic.cn/mmbiz_png/6mychickmupXviaXaRYJ5kCxvoqQcEHQGb0EnEJYsX4ZcegM2GG0vOcvU4E2giaXicoiaXTqhqZAM8ACFJMuQYYDerw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### 步骤

1. **初始标记（CMS initial mark）** 
  
   ![CMS initial mark](https://plumbr.eu/wp-content/uploads/2015/06/g1-06.png)
   
   - 仅仅只是标记一下GCRoots能直接关联到的对象，**速度很快**；（一是标记老年代中所有的GC Roots所指的**直接对象**；二是标记被年轻代中存活对象引用的**直接对象**。）
   
   - 会**Stop the world**
   
2. **并发标记（CMS concurrent mark）**
  
   ![CMS concurrent marking](https://plumbr.eu/wp-content/uploads/2015/06/g1-07.png)
   
   - 从GC Roots的直接关联对象开始遍历整个对象图的过程，**这个过程耗时较长**但是不需要停顿用户线程，可以与垃圾收集线程一起并发运行；
   - 在初始标记的基础上继续往下遍历其他的对象引用并进行标记，该过程会和用户线程**并发**地执行，不会发生停顿。这个阶段会从initial mark阶段中所标记的节点往下检索，标记出所有老年代中存活的对象。注意此时会有部分对象的引用被改变，如上图中的current obj原本所引用的节点已经失去了关联。
   
3. **重新标记（CMS remark）** 

   - 是为了修正并发标记期间，因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间通常会比初始标记阶段稍长一些，但也远比并发标记阶段的时间短
   - 会**Stop the world**

4. **并发清除（CMS concurrent sweep）**

   - 清理删除掉标记阶段判断的已经死亡的对象，由于不需要移动存活对象，所以这个阶段也是可以与用户线程同时并发的



​	由于整个过程耗时最长的并发标记和并发清除过程，收集器都可以与用户线程一起工作，所以，从总体上来说，CMS收集器的内存回收过程是与用户线程并发进行的。



### 缺点

1. 对CPU资源敏感
2. 无法处理浮动垃圾（问题不大，留到下个GC过程再清理）
3. 标记-清除算法会产生内存碎片（可以通过调整+UseCMSCompactAtFullCollection参数来进行内存压缩整理）



