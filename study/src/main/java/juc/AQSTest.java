package juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zz on 2021/11/30.
 */
public class AQSTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            // 业务逻辑
        } finally {
            lock.unlock();
        }
    }

    public static class MySync extends AbstractQueuedSynchronizer{

    }
}
