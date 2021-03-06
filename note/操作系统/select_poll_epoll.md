​          一个对象真正宣告死亡，至少要经历两次标记过程：如果对象在进行可达性分析之后发现没有与GC Roots相连接的引用链，那它将被**第一次标记**并且进行一次筛选。**筛选的条件是此对象是否有必要执行finalize()方法。**

当遇到以下两种情况时，虚拟机将视为“没有必要执行”：

1. 对象是否覆盖finalize()方法
2. finalize()方法已经被虚拟机调用过

​          如果这个对象被判定为有必要执行finalize()方法，那么这个对象将会被放置在一个F-Queue的队列中，并在稍后由一个由虚拟机自动建立的、**低优先级**的Finalizer线程执行。

**注：这里的“执行”指的是虚拟机有机会触发这个方法，但并不承诺会等待它运行结束。**因为一个对象在finalize()方法中执行是非常缓慢的，甚至有可能会发生死循环，将会导致F-Queue队列中其他对象永久处于等待，甚至导致整个内存回收系统崩溃。

finalize()方法是对象逃脱死亡命运的最后一次机会，稍后GC将对F-Queue中的对象进行**第二次小规模的标记，**如果对象在finalize()方法中拯救自己（重新与引用链上任何一个对象建立关联），那么在第二次标记时它将被**移除“即将回收”的集合**，如果对象这时候还没有逃脱，那基本上就真的被回收了~~~


![img](../图片/webp-8995586.)
