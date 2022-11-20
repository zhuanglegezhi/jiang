package interview.ratelimiter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zz on 2022/11/20.
 * <p>
 * 滑动窗口限流器
 */
public class SlidingWindowsRateLimiter implements RateLimiter {

    /**
     * 单位时间划分的小周期（单位时间是1分钟，10s一个小格子窗口，一共6个格子）
     */
    private int SUB_CYCLE = 10;

    /**
     * 每分钟限流请求数
     */
    private int thresholdPerMin = 100;

    /**
     * 计数器, k-为当前窗口的开始时间值秒，value为当前窗口的计数
     */
    private final TreeMap<Long, AtomicLong> counters = new TreeMap<>();


    @Override
    public boolean tryAcquire() {
        long currentWindowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / SUB_CYCLE * SUB_CYCLE; //获取当前时间在哪个小周期窗口
        long currentWindowNum = countCurrentWindow(currentWindowTime); //当前窗口总请求数

        //超过阀值限流
        if (currentWindowNum >= thresholdPerMin) {
            return false;
        }

        //计数器+1
        counters.get(currentWindowTime).incrementAndGet();
        return true;
    }


    /**
     * 统计当前窗口的请求数
     */
    private long countCurrentWindow(long currentWindowTime) {
        //计算窗口开始位置
        long startTime = currentWindowTime - SUB_CYCLE * (60 / SUB_CYCLE - 1);
        long count = 0;

        //遍历存储的计数器
        Iterator<Map.Entry<Long, AtomicLong>> iterator = counters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, AtomicLong> entry = iterator.next();
            // 删除无效过期的子窗口计数器
            if (entry.getKey() < startTime) {
                iterator.remove();
            } else {
                //累加当前窗口的所有计数器之和
                count = count + entry.getValue().get();
            }
        }
        return count;
    }

}
