package interview.block_queue;

/**
 * Created by zz on 2022/9/3.
 * <p>
 * https://zhuanlan.zhihu.com/p/64156753
 */
public class MyBlockingQueue {

    private final Object[] items;

    private int takeIndex;

    private int putIndex;

    private int count;

    public MyBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        items = new Object[capacity];
    }

    public void put(Object obj) throws InterruptedException {
        synchronized (this) {
            while (count == items.length) {
                this.wait();
            }

            enqueue(obj);

            this.notifyAll();
        }
    }


    public Object take() throws InterruptedException {
        synchronized (this) {
            while (count == 0) {
                this.wait();
            }

            Object obj = dequeue();

            this.notifyAll();

            return obj;
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
