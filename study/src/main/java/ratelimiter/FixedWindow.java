package ratelimiter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zz on 2022/6/27.
 */
public class FixedWindow implements IRateLimiter {
    private long lastRequestTime;
    private long qps;
    private AtomicLong count;

    private boolean acquire() {
        long cur = System.currentTimeMillis();
        if (cur - lastRequestTime > 0) {
            count.set(0);
            lastRequestTime = cur;
        }
        if (count.get() < qps) {
            count.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void creat(long qps) {
        this.qps = qps;
    }
}
