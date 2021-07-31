### 用户空间和内核空间

虚拟空间

1. 内核空间
2. 用户空间

现在操作系统都是采用虚拟存储器，那么对32位操作系统而言，它的寻址空间（虚拟存储空间）为4G（2的32次方）。操作系统的核心是内核，独立于普通的应用程序，可以访问受保护的内存空间，也有访问底层硬件设备的所有权限。为了保证用户进程不能直接操作内核（kernel），保证内核的安全，操心系统将虚拟空间划分为两部分，一部分为内核空间，一部分为用户空间。针对linux操作系统而言，将最高的1G字节（从虚拟地址0xC0000000到0xFFFFFFFF），供内核使用，称为内核空间，而将较低的3G字节（从虚拟地址0x00000000到0xBFFFFFFF），供各个进程使用，称为用户空间。



### 进程切换

为了控制进程的执行，内核必须有能力挂起正在CPU上运行的进程，并恢复以前挂起的某个进程的执行。这种行为被称为进程切换。因此可以说，任何进程都是在操作系统内核的支持下运行的，是与内核紧密相关的。

从一个进程的运行转到另一个进程上运行，这个过程中经过下面这些变化：

1. 保存处理机上下文，包括程序计数器和其他寄存器。
2. 更新PCB信息。
3. 把进程的PCB移入相应的队列，如就绪、在某事件阻塞等队列。
4. 选择另一个进程执行，并更新其PCB。
5. 更新内存管理的数据结构。
6. 恢复处理机上下文。

refer: [进程切换](http://guojing.me/linux-kernel-architecture/posts/process-switch/)



### IO

#### 1、同步阻塞IO(BIO)

![图片](https://mmbiz.qpic.cn/mmbiz_png/GLeh42uInXTyY80RSpUTLjIMiaGGicv9zAadgqoGRuEcAClAdesz7WTGhq6ugGbCKNiaghwqyAJJBC1GtVuYpkkmA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)





#### 2、同步非阻塞IO（NIO）

![图片](https://mmbiz.qpic.cn/mmbiz_png/GLeh42uInXTyY80RSpUTLjIMiaGGicv9zA4NCGPZZo9ydSiczrguMdwqFNvibGlzbaopiauFxTqrIa5po5faEAoY7HA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

#### 3、IO多路复用

- IO 多路复用是一种**同步**IO模型，实现一个线程可以监视多个文件句柄；
- 一旦某个文件句柄就绪，就能够通知应用程序进行相应的读写操作；
- 没有文件句柄就绪就会阻塞应用程序，交出CPU。

```
多路是指网络连接，复用指的是同一个线程
```



![图片](https://mmbiz.qpic.cn/mmbiz_png/GLeh42uInXTyY80RSpUTLjIMiaGGicv9zAr5qibfgLBad0zoCEWXxdqC9I4v4mAYLR2SiafwtG4qOmdicHxa1Sx8MKQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

三种实现

- select
- poll
- epoll

refer:

https://www.huaweicloud.com/articles/11750f7c569c6df1494f5790b4edfb85.html






- `sync`: 函数只是将所有修改过的块缓冲区加入写队列，然后就返回，它并不等待实际写磁盘操作结束。所以不要觉得调用了sync函数，就觉得数据已安全的送到磁盘文件上，有可能会出现故障，可是sync函数是无法得知的.通常称为update的系统守护进程会周期性地（一般每隔30秒）调用sync函数。这就保证了定期冲洗内核的块缓冲区。命令sync(1)也调用sync函数**。sync是全局的，对整个系统都flush。**
- `fsync`: 函数**只针对单个文件**，只对由文件描述符filedes指定的单一文件起作用，并且等待写磁盘操作结束，然后返回。fsync不仅会同步更新文件数据，还会同步更新文件的属性（比如atime,mtime等）。fsync可用于数据库这样的应用程序，这种应用程序需要确保将修改过的块立即写到磁盘上。
- `fdatasync`: 当初设计是考虑到有特殊的时候一些基本的元数据比如atime，mtime这些不会对以后读取造成不一致性，因此少了这些元数据的同步可能会在性能上有提升。该函数类似于fsync，但它只影响文件的数据部分,如果该写操作并不影响读取刚写入的数据，则不需等待文件属性被更新。

