https://pdai.tech/md/java/thread/java-thread-x-lock-LockSupport.html

**sun.misc.Unsafe**

```java
// park函数，阻塞线程，并且该线程在下列情况发生之前都会被阻塞: ① 调用unpark函数，释放该线程的许可。② 该线程被中断。③ 设置的时间到了。并且，当time为绝对时间时，isAbsolute为true，否则，isAbsolute为false。当time为0时，表示无限等待，直到unpark发生。
public native void park(boolean isAbsolute, long time);


// unpark函数，释放线程的许可，即激活调用park后阻塞的线程。这个函数不是安全的，调用这个函数时要确保线程依旧存活。
public native void unpark(Thread thread);
```



**LockSupport**

