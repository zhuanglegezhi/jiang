package coupang.circuitbreaker;

/**
 * Created by zz on 2022/5/16.
 */
public class TestSimpleCircuitBreaker {

    public String process(int i) {
        System.out.println("进入process方法,number:" + i);
        throw new RuntimeException(String.valueOf(i));
    }

    public static void main(String[] args) throws Exception {
        SimpleCircuitBreaker circuitBreaker = new SimpleCircuitBreaker(5L);
        TestSimpleCircuitBreaker service = new TestSimpleCircuitBreaker();
        for (int i = 0; i < 10; i++) {
            int temp = i;
            String result = circuitBreaker.call(() -> service.process(temp));
            System.out.println(String.format("返回结果:%s,number:%d", result, temp));
        }
    }

}
