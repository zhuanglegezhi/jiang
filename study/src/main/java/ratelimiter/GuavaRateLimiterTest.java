package ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by zz on 2022/6/27.
 */
public class GuavaRateLimiterTest {

    //比如每秒生产10个令牌，相当于每100ms生产1个令牌
    private RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * 模拟执行业务方法
     */
    public void exeBiz() {
        if (rateLimiter.tryAcquire(1)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "：执行业务逻辑");
        } else {
            System.out.println("线程" + Thread.currentThread().getName() + "：被限流");
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        GuavaRateLimiterTest limiterTest = new GuavaRateLimiterTest();
//        Thread.sleep(500);//等待500ms，让limiter生产一些令牌
//
//        //模拟瞬间生产100个线程请求
//        for (int i = 0; i < 100; i++) {
//            new Thread(limiterTest::exeBiz).start();
//        }


//     testSmoothBursty();
        testSmoothBursty2();
    }

    public static void testSmoothBursty() {
        RateLimiter r = RateLimiter.create(5);
        while (true) {
            System.out.println("get 1 tokens: " + r.acquire() + "s");
        }
        /**
         * output: 基本上都是0.2s执行一次，符合一秒发放5个令牌的设定。
         * get 1 tokens: 0.0s
         * get 1 tokens: 0.182014s
         * get 1 tokens: 0.188464s
         * get 1 tokens: 0.198072s
         * get 1 tokens: 0.196048s
         * get 1 tokens: 0.197538s
         * get 1 tokens: 0.196049s
         */
    }

    public static void testSmoothBursty2() {
        RateLimiter r = RateLimiter.create(2);
        while (true) {
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
            /**
             * output:
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * end
             * get 1 tokens: 0.499796s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             */
        }
    }
}
