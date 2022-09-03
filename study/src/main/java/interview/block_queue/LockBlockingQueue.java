package interview.block_queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zz on 2022/9/3.
 * <p>
 * 显示锁版本
 * <p>
 * https://zhuanlan.zhihu.com/p/64156753
 */
public class LockBlockingQueue {

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    private final Object[] items;

    private int takeIndex;

    private int putIndex;

    private int count;

    public LockBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        items = new Object[capacity];
    }

    public void put(Object obj) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                notFull.await();
            }

            enqueue(obj);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public Object take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }

            Object obj = dequeue();

            notFull.signalAll();

            return obj;
        } finally {
            lock.unlock();
        }
    }


    // 入队，count++
    private void enqueue(Object obj) {
        items[putIndex] = obj;
        if (++putIndex == items.length) {
            putIndex = 0;
        }

        count++;
    }

    private Object dequeue() {
        // 取出takeIndex指向位置中的元素
        // 并将该位置清空
        Object obj = items[takeIndex];
        items[takeIndex] = null;

        // takeIndex向后移一位，如果已到末尾则返回队列开头(位置0)
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }

        count--;
        return obj;
    }

}
