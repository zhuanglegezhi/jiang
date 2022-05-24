package coupang.circuitbreaker;

/**
 * Created by zz on 2022/5/16.
 */
public enum CircuitBreakerStatus {
    /**
     * 关闭
     */
    CLOSED,

    /**
     * 开启
     */
    OPEN,

    /**
     * 半开启
     */
    HALF_OPEN
}
