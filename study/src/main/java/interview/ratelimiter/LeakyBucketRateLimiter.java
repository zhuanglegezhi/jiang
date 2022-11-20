package interview.ratelimiter;

/**
 * Created by zz on 2022/11/20.
 * <p>
 * 漏桶算法
 */
public class LeakyBucketRateLimiter implements RateLimiter {

    /**
     * 每秒处理数（出水率）
     */
    private long rate;

    /**
     * 当前剩余水量
     */
    private long currentWater;

    /**
     * 最后刷新时间
     */
    private long refreshTime;

    /**
     * 桶容量
     */
    private long capacity;


    @Override
    public boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();  //获取系统当前时间
        long outWater = (currentTime - refreshTime) / 1000 * rate; //流出的水量 =(当前时间-上次刷新时间)* 出水率
        currentWater = Math.max(0, currentWater - outWater); // 当前水量 = 之前的桶内水量-流出的水量
        refreshTime = currentTime; // 刷新时间

        // 当前剩余水量还是小于桶的容量，则请求放行
        if (currentWater < capacity) {
            currentWater++;
            return true;
        }

        // 当前剩余水量大于等于桶的容量，限流
        return false;
    }


}
