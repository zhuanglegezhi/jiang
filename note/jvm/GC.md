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



## 2. G1 (Garbage-First Garbage Collector)

- 分代垃圾收集器

  

### 步骤

1. **初始标记阶段 - Initial Marking Phase（STW）**

   - 存活对象的初始标记是捎带在新生代垃圾收集里面，在GC日志里被记录为`GC pause (young)(inital-mark)`

2. **并发标记阶段 - Concurrent Marking Phase**

   ![img](http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/G1GettingStarted/images/slide14.png)

   - 本阶段会与应用程序并发地查找存活的对象，如果找到了空的小堆区（下图中标记为红叉的），他们会在”重新标记阶段“被马上清除。还有决定了活跃度的”accounting“信息也是在本阶段计算的。

3. **重新标记阶段 - Remark Phase（STW）**

   - 对于G1，它短暂地停止应用线程，停止并发更新日志的写入，处理其中的少量信息，并标记所有在并发标记开始时未被标记的存活对象。这一阶段也执行某些额外的清理，如引用处理（参见 Evacuation Pause log）或者类卸载（class unloading）。空的小堆区被清除和回收，并且现在会计算所有小堆区的活跃度。

4. **复制/清除阶段 - Copying/Cleanup Phase（部分STW）**



### Region

传统的GC收集器将连续的内存空间划分为新生代、老年代和永久代（JDK 8去除了永久代，引入了元空间Metaspace），这种划分的特点是各代的存储地址（逻辑地址，下同）**是连续**的。如下图所示：

![传统GC内存布局](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/8a9db36e.png)

而G1的各代存储地址是不连续的，每一代都使用了n个不连续的大小相同的Region，每个Region占有一块连续的虚拟内存地址。如下图所示：

![g1 GC内存布局](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/8ca16868.png)

在上图中，我们注意到还有一些Region标明了H，它代表Humongous，这表示这些Region存储的是巨大对象（humongous object，H-obj），即大小大于等于region一半的对象。H-obj有如下几个特征： 

- H-obj直接分配到了old gen，防止了反复拷贝移动。 
- H-obj在global concurrent marking阶段的cleanup 和 full GC阶段回收。
- 在分配H-obj之前先检查是否超过 initiating heap occupancy percent和the marking threshold, 如果超过的话，就启动global concurrent marking，为的是提早回收，防止 evacuation failures 和 full GC。

### SATB

全称是**Snapshot-At-The-Beginning**，是GC开始时活着的对象的一个快照。它是通过Root Tracing得到的，作用是维持并发GC的正确性。 那么它是怎么维持并发GC的正确性的呢？

根据**三色标记**算法，我们知道对象存在三种状态： 

- 白：对象没有被标记到，标记阶段结束后，会被当做垃圾回收掉。 

- 灰：对象被标记了，但是它的field还没有被标记或标记完。 

- 黑：对象被标记了，且它的所有field也被标记完了

  

### RSets

全称是Remembered Set，是辅助GC过程的一种结构，典型的空间换时间工具，和Card Table有些类似。还有一种数据结构也是辅助GC的：Collection Set（CSet），它记录了GC要收集的Region集合，集合里的Region可以是任意年代的。在GC的时候，对于old->young和old->old的跨代对象引用，只要扫描对应的CSet中的RSet即可。 逻辑上说每个Region都有一个RSet，**RSet记录了其他Region中的对象引用本Region中对象的关系，属于points-into结构（谁引用了我的对象）**。而Card Table则是一种points-out（我引用了谁的对象）的结构，每个Card 覆盖一定范围的Heap（一般为512Bytes）。G1的RSet是在Card Table的基础上实现的：每个Region会记录下别的Region有指向自己的指针，并标记这些指针分别在哪些Card的范围内。 这个RSet其实是一个Hash Table，Key是别的Region的起始地址，Value是一个集合，里面的元素是Card Table的Index。

下图表示了RSet、Card和Region的关系（[出处](http://www.infoq.com/articles/tuning-tips-G1-GC)）：

![Remembered Sets](../图片/5aea17be.jpg)

上图中有三个Region，每个Region被分成了多个Card，在不同Region中的Card会相互引用，Region1中的Card中的对象引用了Region2中的Card中的对象，蓝色实线表示的就是points-out的关系，而在Region2的RSet中，记录了Region1的Card，即红色虚线表示的关系，这就是points-into。 而维系RSet中的引用关系靠post-write barrier和Concurrent refinement threads来维护，操作伪代码如下（[出处](http://hllvm.group.iteye.com/group/topic/44381)）：

```c++
void oop_field_store(oop* field, oop new_value) {
  pre_write_barrier(field);             // pre-write barrier: for maintaining SATB invariant
  *field = new_value;                   // the actual store
  post_write_barrier(field, new_value); // post-write barrier: for tracking cross-region reference
}
```

post-write barrier记录了跨Region的引用更新，更新日志缓冲区则记录了那些包含更新引用的Cards。一旦缓冲区满了，Post-write barrier就停止服务了，会由Concurrent refinement threads处理这些缓冲区日志。 RSet究竟是怎么辅助GC的呢？在做YGC的时候，只需要选定young generation region的RSet作为根集，**这些RSet记录了old->young的跨代引用，避免了扫描整个old generation**。 而mixed gc的时候，old generation中记录了old->old的RSet，young->old的引用由扫描全部young generation region得到，这样也不用扫描全部old generation region。所以RSet的引入大大减少了GC的工作量。

### YGC

选定**所有**年轻代里的Region。通过控制年轻代的region个数，即年轻代内存大小，来控制young GC的时间开销

- 堆从一个单一的内存空间被划分为众多的小堆区（region）。
- 新生代的内存由一系列不连续的小堆区所组成。这使得在需要的时候更加容易进行resize。
- young GC是一个STW事件，所有应用程序线程都会被暂停。
- young GC会使用多线程并行执行。
- 存活的对象将会复制到新的Survivor小堆区或者老年代小堆区。



### Mixed GC

- 选定所有年轻代里的Region，外加根据global concurrent marking统计得出收集收益高的若干老年代Region。在用户指定的开销目标范围内尽可能选择收益高的老年代Region。
- Mixed GC不是full GC，它只能回收部分老年代的Region，如果mixed GC实在无法跟上程序分配内存的速度，导致老年代填满无法继续进行Mixed GC，就会使用serial old GC（full GC）来收集整个GC heap。所以我们可以知道，G1是不提供full GC的。








## Refer

>https://tech.meituan.com/2016/09/23/g1.html
>
>https://blog.chriscs.com/2017/06/20/g1-vs-cms/



