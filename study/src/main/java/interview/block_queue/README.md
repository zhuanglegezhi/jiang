题目要求
- 实现阻塞put
- 实现阻塞take
- 线程安全
  - put: 两个线程各放10个数到队列，使队列为0～19
  - take: 两个线程各取十个数，并且打印
    
注意点
- 如果用Sleep，就性能太差，至少要做到synchronized, wait(), notifyAll()
- 如果用synchronized， 会存在不安全的问题，因为Object的synchronized相关方法都是同步的，如果其他地方用了，容易造成死锁，已经错误唤醒等问题
- 显示锁
  - 同一个condition的话也可以，不过最好区分not_empty和not_full
  - 如果区分了，要注意condition的情况

    
参考
https://zhuanlan.zhihu.com/p/64156753