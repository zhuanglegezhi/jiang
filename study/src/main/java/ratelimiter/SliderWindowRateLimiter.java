package ratelimiter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zz on 2022/6/27.
 */
public class SliderWindowRateLimiter implements Runnable {

    //每秒允许的最大访问数
    private final long maxVisitPerSecond;
    //将每秒时间划分N个块
    private final int block;
    //每个块存储的数量
    private final AtomicLong[] countPerBlock;
    //滑动窗口划到了哪个块儿，可以理解为滑动窗口的起始下标位置
    private volatile int index;
    //目前总的数量
    private AtomicLong allCount;

    /**
     * 构造函数
     *
     * @param block，每秒钟划分N个窗口
     * @param maxVisitPerSecond 每秒最大访问数量
     */
    public SliderWindowRateLimiter(int block, long maxVisitPerSecond) {
        this.block = block;
        this.maxVisitPerSecond = maxVisitPerSecond;
        countPerBlock = new AtomicLong[block];
        for (int i = 0; i < block; i++) {
            countPerBlock[i] = new AtomicLong();
        }
        allCount = new AtomicLong(0);
    }

    /**
     * 判断是否超过最大允许数量
     *
     * @return
     */
    public boolean isOverLimit() {
        return currentQPS() > maxVisitPerSecond;
    }

    /**
     * 获取目前总的访问数
     *
     * @return
     */
    public long currentQPS() {
        return allCount.get();
    }

    /**
     * 请求访问进来，判断是否可以执行业务逻辑
     */
    public void visit() {
        countPerBlock[index].incrementAndGet();
        allCount.incrementAndGet();

        if (isOverLimit()) {
            System.out.println(Thread.currentThread().getName() + "被限流" + "，currentQPS：" + currentQPS() + "，index：" + index);
        } else {
            System.out.println(Thread.currentThread().getName() + "执行业务逻辑" + "，currentQPS：" + currentQPS() + "，index：" + index);
        }
    }

    /**
     * 定时执行器，
     * 每N毫秒滑块移动一次，然后再设置下新滑块的初始化数字0，然后新的请求会落到新的滑块上
     * 同时总数减掉新滑块上的数字，并且重置新的滑块上的数量
     */
    @Override
    public void run() {
        index = (index + 1) % block;
        long val = countPerBlock[index].getAndSet(0);
        allCount.addAndGet(-val);
    }

    public static void main(String[] args) {
        SliderWindowRateLimiter sliderWindowRateLimiter = new SliderWindowRateLimiter(10, 100);

        //固定的速率移动滑块
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(sliderWindowRateLimiter, 100, 100, TimeUnit.MILLISECONDS);

        //模拟不同速度的请求
        new Thread(() -> {
            while (true) {
                sliderWindowRateLimiter.visit();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //模拟不同速度的请求
        new Thread(() -> {
            while (true) {
                sliderWindowRateLimiter.visit();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
