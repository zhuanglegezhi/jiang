package interview.circuitbreaker;

/**
 * Created by zz on 2022/5/16.
 */
public enum CircuitBreakerStatusMonitor {
    /**
     * 单例
     */
    X;

    public void report(String name, CircuitBreakerStatus o, CircuitBreakerStatus n) {
        System.out.println(String.format("断路器[%s]状态变更,[%s]->[%s]", name, o, n));
    }

    public void reset(String name) {
        System.out.println(String.format("断路器[%s]重置", name));
    }
}
