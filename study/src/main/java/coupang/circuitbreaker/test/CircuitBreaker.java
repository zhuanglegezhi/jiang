package coupang.circuitbreaker.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/*
  Created by zz on 2022/5/24.
  题目:
  - 提供用户友好的使用接口
  - 当异常达到阈值（失败率阈值，最小数量阈值）可以进行熔断
  - 如果在熔断状态下可以自恢复（某个时间后，放部分流量进来尝试恢复，都成功则 -> closed，否则opened）
 */
@Slf4j
public class CircuitBreaker {

    //****** 自定义配置项 ****** /
    /**
     * 失败率阈值
     */
    private final double failThreshold;
    /**
     * 最小检测请求数量
     */
    private final long minRequestCount;
    /**
     * HALF_OPEN 状态下尝试恢复请求数（最多只能放这么多请求进来）
     */
    private final long halfOpenRequiredRequestCount;
    /**
     * OPEN -> HALF_OPEN 间隔
     */
    private final long recoverIntervalMs;


    //****** CB统计项 ****** /
    /**
     * CB状态
     */
    private final AtomicReference<Status> status;
    /**
     * 请求数(请求下游资源数目)
     */
    private AtomicLong requestResourceCount;
    /**
     * 请求失败
     */
    private AtomicLong failCount;
    /**
     * HALF_OPEN -> OPEN 的时候已经连续成功数目
     */
    private AtomicLong halfOpenSuccessRequestCount;
    /**
     * 最近一次状态变成OPEN的时间戳
     */
    private long lastOpenMs;

    /**
     * CB Constructor
     *
     * @param failThreshold                CLOSED -> OPEN 的失败率阈值，0 ～ 1
     * @param minRequestCount              CLOSED -> OPEN 最小请求限制
     * @param halfOpenRequiredRequestCount HALF_OPEN -> OPEN 最大请求数，也是需要连续成功的数量
     * @param recoverIntervalMs           HALF_OPEN -> OPEN 最小间隔时间
     */
    public CircuitBreaker(double failThreshold,
                          long minRequestCount,
                          long halfOpenRequiredRequestCount,
                          long recoverIntervalMs) {
        this.failThreshold = failThreshold;
        this.minRequestCount = minRequestCount;
        this.halfOpenRequiredRequestCount = halfOpenRequiredRequestCount;
        this.recoverIntervalMs = recoverIntervalMs;
        this.status = new AtomicReference<>(Status.CLOSED);
        initCircuitBreakerCounts();
    }

    public static CircuitBreaker of(double failThreshold,
                                    long minRequestCount,
                                    long halfOpenRequiredRequestCount,
                                    long halfOpenIntervalMs) {
        return new CircuitBreaker(failThreshold, minRequestCount, halfOpenRequiredRequestCount, halfOpenIntervalMs);
    }

    private void initCircuitBreakerCounts() {
        this.requestResourceCount = new AtomicLong(0);
        this.halfOpenSuccessRequestCount = new AtomicLong(0);
        this.failCount = new AtomicLong(0);
        this.lastOpenMs = -1L;
    }

    public <T> T decorate(Supplier<T> supplier) {
        try {
            if (allowExecute()) {
                T t = supplier.get();
                recordSuccess();
                return t;
            } else {
                log.info("fail-fast, status = [{}]", status.get());
            }
        } catch (Exception e) {
            recordFail();
        }
        return null;
    }

    public void decorate(Runnable runnable) {
        try {
            if (allowExecute()) {
                runnable.run();
                recordSuccess();
            } else {
                log.info("fail-fast, status = [{}]", status.get());
            }
        } catch (Exception e) {
            recordFail();
        }
    }

    private boolean allowExecute() {
        Status st = this.status.get();

        if (st == Status.CLOSED) {
            return true;
        }

        if (st == Status.HALF_OPEN) {
            // 这里考察要不要少放量一点进来
            return this.halfOpenSuccessRequestCount.get() < halfOpenRequiredRequestCount;
        }

        return afterRecoverInterval() && tryChangeStatus(Status.OPEN, Status.HALF_OPEN);
    }

    private boolean afterRecoverInterval() {
        return System.currentTimeMillis() >= this.lastOpenMs + this.recoverIntervalMs;
    }

    private double currentFailRate() {
        return 1.0 * failCount.get() / requestResourceCount.get();
    }

    private void recordSuccess() {
        this.requestResourceCount.incrementAndGet();
        if (status.get() == Status.HALF_OPEN) {
            if (halfOpenSuccessRequestCount.incrementAndGet() >= this.halfOpenRequiredRequestCount && tryChangeStatus(Status.HALF_OPEN, Status.CLOSED)) {
                initCircuitBreakerCounts();
            }
        }
    }

    private void recordFail() {
        long requestCount = this.requestResourceCount.incrementAndGet();
        this.failCount.incrementAndGet();

        if (tryChangeStatus(Status.HALF_OPEN, Status.OPEN)) {
            // HALF_OPEN状态下, 失败一次即重新OPEN
            this.halfOpenSuccessRequestCount = new AtomicLong(0);
            this.lastOpenMs = System.currentTimeMillis();
        }
        if (requestCount >= minRequestCount && currentFailRate() > failThreshold && tryChangeStatus(Status.CLOSED, Status.OPEN)) {
            this.lastOpenMs = System.currentTimeMillis();
        }
    }

    private boolean tryChangeStatus(Status s1, Status s2) {
        if (status.compareAndSet(s1, s2)) {
            log.info("status changes, [{}] => [{}]", s1, s2);
            return true;
        }
        return false;
    }

    enum Status {
        CLOSED,
        OPEN,
        HALF_OPEN;
    }

}
