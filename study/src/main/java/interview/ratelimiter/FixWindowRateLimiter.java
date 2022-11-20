package interview.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zz on 2022/11/20.
 * <p>
 * 固定窗口时间算法
 * <p>
 * 这种算法有一个很明显的临界问题：假设限流阀值为5个请求，单位时间窗口是1s,如果我们在单位时间内的前0.8-1s和1-1.2s，分别并发5个请求。虽然都没有超过阀值，但是如果算0.8-1.2s,则并发数高达10，已经超过单位时间1s不超过5阀值的定义啦。
 */
public class FixWindowRateLimiter implements RateLimiter {

    private long lastRequestTime = System.currentTimeMillis();

    /**
     * 时间窗口（秒）
     */
    private long windowUnit;

    private AtomicLong counter;

    private long threshold;


    @Override
    public boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();  //获取系统当前时间
        if (currentTime - lastRequestTime > windowUnit) {  //检查是否在时间窗口内
            counter.set(0);  // 计数器清0
            lastRequestTime = currentTime;  //开启新的时间窗口
        }

        if (counter.get() < threshold) {  // 小于阀值
            counter.incrementAndGet(); //计数器加1
            return true;
        }

        return false;
    }

}
