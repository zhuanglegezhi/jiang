## Young GC日志

```shell
{Heap before GC invocations=12 (full 1):
 garbage-first heap   total 3145728K, used 336645K [0x0000000700000000, 0x00000007c0000000, 0x00000007c0000000)
  region size 1024K, 172 young (176128K), 13 survivors (13312K)
 Metaspace       used 29944K, capacity 30196K, committed 30464K, reserved 1077248K
  class space    used 3391K, capacity 3480K, committed 3584K, reserved 1048576K
2014-11-14T17:57:23.654+0800: 27.884: [GC pause (G1 Evacuation Pause) (young)
Desired survivor size 11534336 bytes, new threshold 15 (max 15)
- age   1:    5011600 bytes,    5011600 total
 27.884: [G1Ergonomics (CSet Construction) start choosing CSet, _pending_cards: 1461, predicted base time: 35.25 ms, remaining time: 64.75 ms, target pause time: 100.00 ms]
 27.884: [G1Ergonomics (CSet Construction) add young regions to CSet, eden: 159 regions, survivors: 13 regions, predicted young region time: 44.09 ms]
 27.884: [G1Ergonomics (CSet Construction) finish choosing CSet, eden: 159 regions, survivors: 13 regions, old: 0 regions, predicted pause time: 79.34 ms, target pause time: 100.00 ms]
, 0.0158389 secs]
   [Parallel Time: 8.1 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 27884.5, Avg: 27884.5, Max: 27884.5, Diff: 0.1]
      [Ext Root Scanning (ms): Min: 0.4, Avg: 0.8, Max: 1.2, Diff: 0.8, Sum: 3.1]
      [Update RS (ms): Min: 0.0, Avg: 0.3, Max: 0.6, Diff: 0.6, Sum: 1.4]
         [Processed Buffers: Min: 0, Avg: 2.8, Max: 5, Diff: 5, Sum: 11]
      [Scan RS (ms): Min: 0.0, Avg: 0.1, Max: 0.1, Diff: 0.1, Sum: 0.3]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.6]
      [Object Copy (ms): Min: 4.9, Avg: 5.1, Max: 5.2, Diff: 0.3, Sum: 20.4]
      [Termination (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.4, Max: 1.3, Diff: 1.3, Sum: 1.4]
      [GC Worker Total (ms): Min: 6.4, Avg: 6.8, Max: 7.8, Diff: 1.4, Sum: 27.2]
      [GC Worker End (ms): Min: 27891.0, Avg: 27891.3, Max: 27892.3, Diff: 1.3]
   [Code Root Fixup: 0.5 ms]
   [Code Root Migration: 1.3 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.2 ms]
   [Other: 5.8 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 5.0 ms]
      [Ref Enq: 0.1 ms]
      [Redirty Cards: 0.0 ms]
      [Free CSet: 0.2 ms]
   [Eden: 159.0M(159.0M)->0.0B(301.0M) Survivors: 13.0M->11.0M Heap: 328.8M(3072.0M)->167.3M(3072.0M)]
Heap after GC invocations=13 (full 1):
 garbage-first heap   total 3145728K, used 171269K [0x0000000700000000, 0x00000007c0000000, 0x00000007c0000000)
  region size 1024K, 11 young (11264K), 11 survivors (11264K)
 Metaspace       used 29944K, capacity 30196K, committed 30464K, reserved 1077248K
  class space    used 3391K, capacity 3480K, committed 3584K, reserved 1048576K
}
 [Times: user=0.05 sys=0.01, real=0.02 secs]
```

### 详解

> garbage-first heap total 3145728K, used 336645K [0x0000000700000000, 0x00000007c0000000, 0x00000007c0000000) 

表示使用了G1垃圾收集器，total heap 3145728K，使用了336645K。

> region size 1024K, 172 young (176128K), 13 survivors (13312K)

 Region大小为1M，青年代占用了172个（共176128K），幸存区占用了13个（共13312K）。 

> Metaspace used 29944K, capacity 30196K, committed 30464K, reserved 1077248K class space used 3391K, capacity 3480K, committed 3584K, reserved 1048576K 

java 8的新特性，去掉永久区，添加了元数据区，这块不是本文重点，不再赘述。需要注意的是，之所以有committed和reserved，是因为没有设置MetaspaceSize=MaxMetaspaceSize。

> [GC pause (G1 Evacuation Pause) (young) 

GC原因，新生代minor GC。 

> [G1Ergonomics (CSet Construction) start choosing CSet, _pending_cards: 1461, predicted base time: 35.25 ms, remaining time: 64.75 ms, target pause time: 100.00 ms] 

发生minor GC和full GC时，所有相关region都是要回收的。而发生并发GC时，会根据目标停顿时间动态选择部分垃圾对并多的Region回收，这一步就是选择Region。_pending_cards是关于RSet的Card Table。predicted base time是预测的扫描card table时间。 

> [G1Ergonomics (CSet Construction) add young regions to CSet, eden: 159 regions, survivors: 13 regions, predicted young region time: 44.09 ms] 

这一步是添加Region到collection set，新生代一共159个Region，13个幸存区Region，这也和之前的（172 young (176128K), 13 survivors (13312K)）吻合。预计收集时间是44.09 ms。 

> [G1Ergonomics (CSet Construction) finish choosing CSet, eden: 159 regions, survivors: 13 regions, old: 0 regions, predicted pause time: 79.34 ms, target pause time: 100.00 ms] 

这一步是对上面两步的总结。预计总收集时间79.34ms。 

> [Parallel Time: 8.1 ms, GC Workers: 4]

由于收集过程是多线程并行（并发）进行，这里是4个线程，总共耗时8.1ms（wall clock time） 

> [GC Worker Start (ms): Min: 27884.5, Avg: 27884.5, Max: 27884.5, Diff: 0.1] 

收集线程开始的时间，使用的是相对时间，Min是最早开始时间，Avg是平均开始时间，Max是最晚开始时间，Diff是Max-Min（此处的0.1貌似有问题） 

> [Ext Root Scanning (ms): Min: 0.4, Avg: 0.8, Max: 1.2, Diff: 0.8, Sum: 3.1] 

扫描Roots花费的时间，Sum表示total cpu time，下同。 

> [Update RS (ms): Min: 0.0, Avg: 0.3, Max: 0.6, Diff: 0.6, Sum: 1.4] [Processed Buffers: Min: 0, Avg: 2.8, Max: 5, Diff: 5, Sum: 11] 

Update RS (ms)是每个线程花费在更新Remembered Set上的时间。 

> [Scan RS (ms): Min: 0.0, Avg: 0.1, Max: 0.1, Diff: 0.1, Sum: 0.3] 

扫描CS中的region对应的RSet，因为RSet是points-into，所以这样实现避免了扫描old generadion region，但是会产生float garbage。 

> [Code Root Scanning (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.6] 

扫描code root耗时。code root指的是经过JIT编译后的代码里，引用了heap中的对象。引用关系保存在RSet中。 

> [Object Copy (ms): Min: 4.9, Avg: 5.1, Max: 5.2, Diff: 0.3, Sum: 20.4] 

拷贝活的对象到新region的耗时。 

> [Termination (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0] 

线程结束，在结束前，它会检查其他线程是否还有未扫描完的引用，如果有，则”偷”过来，完成后再申请结束，这个时间是线程之前互相同步所花费的时间。 

> [GC Worker Other (ms): Min: 0.0, Avg: 0.4, Max: 1.3, Diff: 1.3, Sum: 1.4] 

花费在其他工作上（未列出）的时间。 

> [GC Worker Total (ms): Min: 6.4, Avg: 6.8, Max: 7.8, Diff: 1.4, Sum: 27.2] 

每个线程花费的时间和。 

> [GC Worker End (ms): Min: 27891.0, Avg: 27891.3, Max: 27892.3, Diff: 1.3] 

每个线程结束的时间。 

> [Code Root Fixup: 0.5 ms] 

用来将code root修正到正确的evacuate之后的对象位置所花费的时间。 

> [Code Root Migration: 1.3 ms] 

更新code root 引用的耗时，code root中的引用因为对象的evacuation而需要更新。 

[Code Root Purge: 0.0 ms] 

清除code root的耗时，code root中的引用已经失效，不再指向Region中的对象，所以需要被清除。 

> [Clear CT: 0.2 ms] 

清除card table的耗时。 

> [Other: 5.8 ms] [Choose CSet: 0.0 ms] [Ref Proc: 5.0 ms] [Ref Enq: 0.1 ms] [Redirty Cards: 0.0 ms] [Free CSet: 0.2 ms] 

其他事项共耗时5.8ms，其他事项包括选择CSet，处理已用对象，引用入ReferenceQueues，释放CSet中的region到free list。 

> [Eden: 159.0M(159.0M)->0.0B(301.0M) Survivors: 13.0M->11.0M Heap: 328.8M(3072.0M)->167.3M(3072.0M)] 新生代清空了，下次扩容到301MB。



### 新生代收集

和其他垃圾收集器一样，G1也使用`-XX:PrintGCDetails`打印出详细的垃圾收集日志，下面这张图是新生代收集的标准流程，我在这里将它分成了6个步骤：



![G1的新生代垃圾收集](../../图片/16c127f0471dface~tplv-t2oaga2asx-watermark.awebp)



1. 四个关键信息

   - 新生代垃圾收集发生的时间——**2016-12-12T10:40:18.811-0500**，通过设置`-XX:+PrintGCDateStamps`参数打印出这个时间；
   - JVM启动后的相对时间——**25.959**
   - 这次收集的类型——新生代收集，只回收Eden分区
   - 这次收集花费的时间——**0.0305171s，即30ms**

2. 列出了新生代收集中并行收集的详细过程

   - **Parallel Time**：并行收集任务在运行过程中引发的STW（Stop The World）时间，从新生代垃圾收集开始到最后一个任务结束，共花费26.6ms

   - **GC Workers**：有4个线程负责垃圾收集，通过参数`-XX:ParallelGCThreads`设置，这个参数的值的设置，跟CPU有关，如果物理CPU支持的线程个数小于8，则最多设置为8；如果物理CPU支持的线程个数大于8，则默认值为number * 5/8

   - **GC Worker Start**：第一个垃圾收集线程开始工作时JVM启动后经过的时间（min）；最后一个垃圾收集线程开始工作时JVM启动后经过的时间（max）；diff表示min和max之间的差值。理想情况下，你希望他们几乎是同时开始，即diff趋近于0。

   - **Ext Root Scanning**：扫描root集合（线程栈、JNI、全局变量、系统表等等）花费的时间，扫描root集合是垃圾收集的起点，尝试找到是否有root集合中的节点指向当前的收集集合（CSet）

   - **Update RS(Remembered Set or RSet)**：每个分区都有自己的RSet，用来记录其他分区指向当前分区的指针，如果RSet有更新，G1中会有一个post-write barrier管理跨分区的引用——新的被引用的card会被标记为dirty，并放入一个日志缓冲区，如果这个日志缓冲区满了会被加入到一个全局的缓冲区，在JVM运行的过程中还有线程在并发处理这个全局日志缓冲区的dirty card。

     Update RS

     表示允许垃圾收集线程处理本次垃圾收集开始前没有处理好的日志缓冲区，这可以确保当前分区的RSet是最新的。

     - **Processed Buffers**，这表示在Update RS这个过程中处理多少个日志缓冲区。

   - **Scan RS**：扫描每个新生代分区的RSet，找出有多少指向当前分区的引用来自CSet。

   - **Code Root Scanning**：扫描代码中的root节点（局部变量）花费的时间

   - **Object Copy**：在疏散暂停期间，所有在CSet中的分区必须被转移疏散，Object Copy就负责将当前分区中存活的对象拷贝到新的分区。

   - **Termination**：当一个垃圾收集线程完成任务时，它就会进入一个临界区，并尝试帮助其他垃圾线程完成任务（steal outstanding tasks），min表示该垃圾收集线程什么时候尝试terminatie，max表示该垃圾收集回收线程什么时候真正terminated。

     - **Termination Attempts**：如果一个垃圾收集线程成功盗取了其他线程的任务，那么它会再次盗取更多的任务或再次尝试terminate，每次重新terminate的时候，这个数值就会增加。

   - **GC Worker Other**：垃圾收集线程在完成其他任务的时间

   - **GC Worker Total**：展示每个垃圾收集线程的最小、最大、平均、差值和总共时间。

   - **GC Worker End**：min表示最早结束的垃圾收集线程结束时该JVM启动后的时间；max表示最晚结束的垃圾收集线程结束时该JVM启动后的时间。理想情况下，你希望它们快速结束，并且最好是同一时间结束。

3. 列出了新生代GC中的一些任务：

   - **Code Root Fixup** ：释放用于管理并行垃圾收集活动的数据结构，应该接近于0，该步骤是线性执行的；
   - **Code Root Purge**：清理更多的数据结构，应该很快，耗时接近于0，也是线性执行。
   - **Clear CT**：清理card table

4. 包含一些扩展功能

   - **Choose CSet**：选择要进行回收的分区放入CSet（G1选择的标准是垃圾最多的分区优先，也就是存活对象率最低的分区优先）
   - **Ref Proc**：处理Java中的各种引用——soft、weak、final、phantom、JNI等等。
   - **Ref Enq**：遍历所有的引用，将不能回收的放入pending列表
   - **Redirty Card**：在回收过程中被修改的card将会被重置为dirty
   - **Humongous Register**：JDK8u60提供了一个特性，巨型对象可以在新生代收集的时候被回收——通过`G1ReclaimDeadHumongousObjectsAtYoungGC`设置，默认为true。
   - **Humongous Reclaim**：做下列任务的时间：确保巨型对象可以被回收、释放该巨型对象所占的分区，重置分区类型，并将分区还到free列表，并且更新空闲空间大小。
   - **Free CSet**：将要释放的分区还回到free列表。

5. 展示了不同代的大小变化，以及堆大小的自适应调整。

   - **Eden:1097.0M(1097.0M)->0.0B(967.0M)**：（1）当前新生代收集触发的原因是Eden空间满了，分配了1097M，使用了1097M；（2）所有的Eden分区都被疏散处理了，在新生代结束后Eden分区的使用大小成为了0.0B；（3）Eden分区的大小缩小为967.0M
   - **Survivors:13.0M->139.0M**：由于年轻代分区的回收处理，survivor的空间从13.0M涨到139.0M；
   - **Heap:1694.4M(2048.0M)->736.3M(2048.0M)**：（1）在本次垃圾收集活动开始的时候，堆空间整体使用量是1694.4M，堆空间的最大值是2048M；（2）在本次垃圾收集结束后，堆空间的使用量是763.4M，最大值保持不变。

6. 第6点展示了本次新生代垃圾收集的时间

   - **user=0.8**：垃圾收集线程在新生代垃圾收集过程中消耗的CPU时间，这个时间跟垃圾收集线程的个数有关，可能会比real time大很多；
   - **sys=0.0**：内核态线程消耗的CPU时间 -**real=0.03**：本次垃圾收集真正消耗的时间；

### 并发垃圾收集

G1的第二种收集活动是并发垃圾收集，并发垃圾收集的触发条件有很多，但是做的工作都相同，它的日志如下图所示：

![G1收集器的并发垃圾收集日志](../图片/16c127f04a806083~tplv-t2oaga2asx-watermark.awebp)



1. 标志着并发垃圾收集阶段的开始：
   - **GC pause(G1 Evacuation Pause)(young)(initial-mark)**：为了充分利用STW的机会来trace所有可达（存活）的对象，initial-mark阶段是作为新生代垃圾收集中的一部分存在的（搭便车）。initial-mark设置了两个TAMS（top-at-mark-start）变量，用来区分存活的对象和在并发标记阶段新分配的对象。在TAMS之前的所有对象，在当前周期内都会被视作存活的。
2. 表示第并发标记阶段做的第一个事情：根分区扫描
   - **GC concurrent-root-region-scan-start**：根分区扫描开始，根分区扫描主要扫描的是新的survivor分区，找到这些分区内的对象指向当前分区的引用，如果发现有引用，则做个记录；
   - **GC concurrent-root-region-scan-end**：根分区扫描结束，耗时0.0030613s
3. 表示并发标记阶段
   - **GC Concurrent-mark-start**：并发标记阶段开始。（1）并发标记阶段的线程是跟应用线程一起运行的，不会STW，所以称为并发；并发标记阶段的垃圾收集线程，默认值是Parallel Thread个数的25%，这个值也可以用参数`-XX:ConcGCThreads`设置；（2）trace整个堆，并使用位图标记所有存活的对象，因为在top TAMS之前的对象是隐式存活的，所以这里只需要标记出那些在top TAMS之后、阈值之前的；（3）记录在并发标记阶段的变更，G1这里使用了SATB算法，该算法要求在垃圾收集开始的时候给堆做一个快照，在垃圾收集过程中这个快照是不变的，但实际上肯定有些对象的引用会发生变化，这时候G1使用了pre-write barrier记录这种变更，并将这个记录存放在一个SATB缓冲区中，如果该缓冲区满了就会将它加入到一个全局的缓冲区，同时G1有一个线程在并行得处理这个全局缓冲区；（4）在并发标记过程中，会记录每个分区的存活对象占整个分区的大小的比率；
   - **GC Concurrent-mark-end**：并发标记阶段结束，耗时0.3055438s
4. 重新标记阶段，会Stop the World
   - **Finalize Marking**：Finalizer列表里的Finalizer对象处理，耗时0.0014099s；
   - **GC ref-proc**：引用（soft、weak、final、phantom、JNI等等）处理，耗时0.0000480s；
   - **Unloading**：类卸载，耗时0.0025840s；
   - 除了前面这几个事情，这个阶段最关键的结果是：绘制出当前并发周期中整个堆的最后面貌，剩余的SATB缓冲区会在这里被处理，所有存活的对象都会被标记；
5. 清理阶段，也会Stop the World
   - 计算出最后存活的对象：标记出initial-mark阶段后分配的对象；标记出至少有一个存活对象的分区；
   - 为下一个并发标记阶段做准备，previous和next位图会被清理；
   - 没有存活对象的老年代分区和巨型对象分区会被释放和清理；
   - 处理没有任何存活对象的分区的RSet；
   - 所有的老年代分区会按照自己的存活率（存活对象占整个分区大小的比例）进行排序，为后面的CSet选择过程做准备；
6. 并发清理阶段
   - **GC concurrent-cleanup-start**：并发清理阶段启动。完成第5步剩余的清理工作；将完全清理好的分区加入到二级free列表，等待最终还会到总体的free列表；
   - **GC concurrent-cleanup-end**：并发清理阶段结束，耗时0.0012954s

### 混合收集

在并发收集阶段结束后，你会看到混合收集阶段的日志，如下图所示，该日志的大部分跟之前讨论的新生代收集相同，只有第1部分不一样：**GC pause(G1 Evacuation Pause)(mixed),0.0129474s**，这一行表示这是一个混合垃圾收集周期；在混合垃圾收集处理的CSet不仅包括新生代的分区，还包括老年代分区——也就是并发标记阶段标记出来的那些老年代分区。

![G1的混合收集](../../图片/16c127f04ab0adc9~tplv-t2oaga2asx-watermark.awebp)



### Full GC

如果堆内存空间不足以分配新的对象，或者是Metasapce空间使用率达到了设定的阈值，那么就会触发Full GC——你在使用G1的时候应该尽量避免这种情况发生，因为G1的Full Gc是单线程、会Stop The World，代价非常高。Full GC的日志如下图所示，从中你可以看出三类信息

1. Full GC的原因，这个图里是Allocation Failure，还有一个常见的原因是Metadata GC Threshold；
2. Full GC发生的频率，每隔几天发生一次Full GC还可以接受，但是每隔1小时发生一次Full GC则不可接受；
3. Full GC的耗时，这张图里的Full GC耗时150ms（PS：按照我的经验，实际运行中如果发生Full GC，耗时会比这个多很多）



![G1的Full GC](../../图片/16c127f06b33bfc9~tplv-t2oaga2asx-watermark.awebp)



基础配置参数中，我这里还想介绍两个：`-XX:+PrintGCApplicationStoppedTime`和`-XX:+PrintGCApplicationConcurrentTime`，这两个参数也可以为你提供有用的信息，如下图所示：

![其他信息](../../图片/16c127f04c1d30bc~tplv-t2oaga2asx-watermark.awebp)



1. 记录了应用线程在安全点被暂停的总时间（也就是STW的总时间）
2. 记录了让所有应用线程进入安全点所花费的总时间
3. 记录了在两个安全点之间应用线程运行的时间

