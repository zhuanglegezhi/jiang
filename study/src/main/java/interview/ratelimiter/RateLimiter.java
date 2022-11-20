package interview.ratelimiter;

/**
 * Created by zz on 2022/11/20.
 */
public interface RateLimiter {
    boolean tryAcquire();
}
