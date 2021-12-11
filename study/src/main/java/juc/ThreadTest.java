package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zz on 2021/12/11.
 * <p>
 * 多线程交替打印
 */
public class ThreadTest {

    public static void main(String[] args) {
        SyncWay.test();
//        LockWay.test();
    }

    static class SyncWay {

        private static int count = 0;
        private static final Object LOCK = new Object();

        private static void test() {
            System.out.println("===== SyncWay start =====");
            Thread thread1 = new MyThread(0);
            Thread thread2 = new MyThread(1);
            Thread thread3 = new MyThread(2);

            thread1.start();
            thread2.start();
            thread3.start();

        }

        static class MyThread extends Thread {
            private int order;

            public MyThread(int order) {
                this.order = order;
            }

            @Override
            public void run() {
                synchronized (LOCK) {
                    while (count < 100) {
                        if (count % 3 != order) {
                            try {
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        if (count < 100) {
                            ++count;
                            System.out.println(Thread.currentThread().getName() + "      count: " + count);
                        }
                        LOCK.notifyAll();
                    }
                }
            }
        }
    }

    static class LockWay {

        private static int count = 0;
        private static final Lock LOCK = new ReentrantLock();
        private static final Condition c1 = LOCK.newCondition();
        private static final Condition c2 = LOCK.newCondition();
        private static final Condition c3 = LOCK.newCondition();

        private static void test() {
            System.out.println("===== LockWay start =====");

            MyThread thread1 = new MyThread(c1, c2);
            MyThread thread2 = new MyThread(c2, c3);
            MyThread thread3 = new MyThread(c3, c1);

            thread1.start();
            thread2.start();
            thread3.start();
        }

        static class MyThread extends Thread {

            private final Condition condition;
            private final Condition nextCondition;

            public MyThread(Condition condition, Condition nextCondition) {
                this.condition = condition;
                this.nextCondition = nextCondition;
            }

            @Override
            public void run() {
                while (count < 100) {
                    LOCK.lock();
                    try {
                        count++;
                        System.out.println(Thread.currentThread().getName() + "      count: " + count);

                        nextCondition.signal();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        LOCK.unlock();
                    }
                }

            }
        }
    }
}