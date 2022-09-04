package juc;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by zz on 2022/9/4.
 */
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    private static void test1() throws InterruptedException {
        Object o = new Object();
        System.out.println("还没有进入到同步块");
        System.out.println("markword:" + ClassLayout.parseInstance(o).toPrintable());
        //默认JVM启动会有一个预热阶段，所以默认不会开启偏向锁
        Thread.sleep(5000);
        Object b = new Object();
        System.out.println("还没有进入到同步块");
        System.out.println("markword:" + ClassLayout.parseInstance(b).toPrintable());
        synchronized (o) {
            System.out.println("进入到了同步块");
            System.out.println("markword:" + ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 通过在控制台中的打印内容我们可以发现，锁的状态一共经历了以下几个变化步骤：
     *
     * biasable状态
     * 在这个状态下，锁是一个待偏向的状态，此时如果有线程请求的话。不过如果是刚启动JVM的状态的话，对象头部会是non-biasable状态，只有等jvm预热了一段时间（大约是4秒），才会留转变为biasable状态。
     *
     * biased状态
     * 当第一个请求获取到锁的时候，锁的状态会变成偏向锁状态，也就是biased。如果在处于偏向锁状态的时候，还有新的线程参与锁的抢夺，那么就会发生锁的升级，进入到轻量级锁状态阶段。
     *
     * thin lock状态
     * 可以看到，当一个锁已经经历过偏向锁状态之后，后去如果再有其他线程访问它，它就会升级为轻量级锁的状态，也就是thin lock状态。
     *
     * fat lock状态
     * 当我们在同步代码块中调用hashcode方法的时候，会发现，锁的对象头部会多出一个叫做fat lock的状态，这就意味着，此时该锁已经升级为了重量级锁的状态了。
     */
    private static void test2() throws InterruptedException {
        // 睡眠 5s
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println("未进入同步块，MarkWord 为：");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(("进入同步块，MarkWord 为："));
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        Thread t2 = new Thread(() -> {
            synchronized (o) {
                System.out.println("新线程获取锁，MarkWord为：");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });
        t2.start();
        t2.join();
        System.out.println("主线程再次查看锁对象，MarkWord为：");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(("主线程再次进入同步块，MarkWord 为："));
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        synchronized (o) {
            System.out.println(("主线程再次进入同步块，并且调用hashcode方法，MarkWord 为："));
            o.hashCode();
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

}
