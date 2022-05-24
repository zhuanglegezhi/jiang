package coupang.circuitbreaker.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * Created by zz on 2022/5/24.
 */
@Slf4j
public class CircuitBreaker {

    /**
     * 失败率阈值
     */
    private final double failThreshold;
    /**
     * 最小检测请求数量
     */
    private final long minRequestCount;
    /**
     * HALF_OPEN 状态下尝试恢复请求数, （最多只能放这么多请求进来）
     */
    private final long recoverRequiredRequestCount;
    /**
     * OPEN -> HALF_OPEN 间隔
     */
    private final long recoverIntervalMs;
    /**
     * CB状态
     */
    private final AtomicReference<Status> status;

    /**
     * 请求数
     */
    private AtomicLong requestCount;
    /**
     * HALF_OPEN -> OPEN 的时候已经连续成功数目
     */
    private AtomicLong recoverSuccessRequestCount;
    /**
     * 请求失败
     */
    private AtomicLong failCount;
    /**
     * 最近一次状态变成OPEN的时间戳
     */
    private long lastFailMs;


    public CircuitBreaker(double failThreshold, long minRequestCount, long recoverRequiredRequestCount, long recoverIntervalMs) {
        // 可配置值
        this.failThreshold = failThreshold;
        this.minRequestCount = minRequestCount;
        this.recoverRequiredRequestCount = recoverRequiredRequestCount;
        this.recoverIntervalMs = recoverIntervalMs;

        this.status = new AtomicReference<>(Status.CLOSED);
        initCounts();
    }

    private void initCounts() {
        this.requestCount = new AtomicLong(0);
        this.recoverSuccessRequestCount = new AtomicLong(0);
        this.failCount = new AtomicLong(0);
        this.lastFailMs = -1L;
    }

    public <T> T decorate(Supplier<T> supplier) {
        try {
            if (allowExecute()) {
                T t = supplier.get();
                recordSuccess();
                return t;
            } else {
                log.info("fail-fast, status={}", status.get());
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
                log.info("fail-fast, status={}", status.get());
            }
        } catch (Exception e) {
            recordFail();
        }
    }

    private boolean allowExecute() {
        if (status.get() == Status.CLOSED) {
            return true;
        }
        if (status.get() == Status.HALF_OPEN) {
            // 这里考察要不要少放量一点进来
            return this.recoverSuccessRequestCount.get() <= recoverRequiredRequestCount;
        }
        return shouldRecover() && (changeStatus(Status.OPEN, Status.HALF_OPEN));
    }

    private boolean shouldRecover() {
        return System.currentTimeMillis() >= this.lastFailMs + this.recoverIntervalMs;
    }

    private double currentFailRate() {
        return 1.0 * failCount.get() / requestCount.get();
    }

    private void recordSuccess() {
        this.requestCount.incrementAndGet();
        if (status.get() == Status.HALF_OPEN) {
            if (recoverSuccessRequestCount.incrementAndGet() >= this.recoverRequiredRequestCount && changeStatus(Status.HALF_OPEN, Status.CLOSED)) {
                initCounts();
            }
        }
    }

    private void recordFail() {
        long _requestCount = this.requestCount.incrementAndGet();
        this.failCount.incrementAndGet();

        if (changeStatus(Status.HALF_OPEN, Status.OPEN)) {
            // HALF_OPEN状态下, 失败一次即重新OPEN
            this.recoverSuccessRequestCount = new AtomicLong(0);
            this.lastFailMs = System.currentTimeMillis();
        }
        if (_requestCount >= minRequestCount && currentFailRate() > failThreshold && changeStatus(Status.CLOSED, Status.OPEN)) {
            this.lastFailMs = System.currentTimeMillis();
        }
    }

    private boolean changeStatus(Status s1, Status s2) {
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
