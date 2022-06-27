package ratelimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zz on 2022/6/27.
 */
public class MyRateLimiter {

    // 任务队列，任务队列的数量就代表限流器允许通过的最大线程数
    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    static {
        try {
            blockingQueue.put(new Object());
            blockingQueue.put(new Object());
            blockingQueue.put(new Object());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 生产者
    public void product() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);

        // scheduleAtFixedRate 以固定的频率 上一个任务开始的时间计时，一个period后，检测上一个任务是否执行完毕，
        // 如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行
        // scheduleWithFixedDelay 以固定的延时 ，delay（延时）指的是一次执行终止和下一次执行开始之间的延迟
        ses.scheduleWithFixedDelay(() -> {
            try {
                blockingQueue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 4, 4, TimeUnit.SECONDS);
    }

    // 限流器
    public void acquire() {
        try {
            // 阻塞获取
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
