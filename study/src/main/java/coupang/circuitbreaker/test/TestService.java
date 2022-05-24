package coupang.circuitbreaker.test;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zz on 2022/5/24.
 */
@Slf4j
public class TestService {

    public static void main(String[] args) throws InterruptedException {
        TestService service = new TestService();
        CircuitBreaker cb = new CircuitBreaker(0.5, 5, 3, 1000L);
        // CLOSED -> OPEN
        for (int i = 0; i < 10; i++) {
            int temp = i;
            cb.decorate(() -> service.processFail(temp));
        }

        // 没到recover时间，fail-fast
        System.out.println("开始模拟成功调用========");
        for (int i = 0; i < 10; i++) {
            cb.decorate(service::processSuccess);
        }
        System.out.println("结束模拟成功调用========");

        System.out.println("开始睡1000ml========");
        Thread.sleep(1000L);

        System.out.println("开始模拟成功调用========");
        for (int i = 0; i < 10; i++) {
            cb.decorate(service::processSuccess);
        }
        System.out.println("结束模拟成功调用========");

        System.out.println("开始模拟失败调用========");
        for (int i = 0; i < 100; i++) {
            int temp = i;
            String result = cb.decorate(() -> service.processFail(temp));
            System.out.println(String.format("返回结果:%s,number:%d", result, temp));
        }
        System.out.println("结束模拟失败调用========");
    }

    public String processFail(int i) {
        System.out.println("失败调用, number:" + i);
        throw new RuntimeException(String.valueOf(i));
    }

    public String processSuccess() {
        System.out.println("成功调用");
        return "success";
    }
}
