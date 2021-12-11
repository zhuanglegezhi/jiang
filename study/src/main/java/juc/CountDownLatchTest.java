package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zz on 2021/12/11.
 */
public class CountDownLatchTest {

    private static int num = 3;
    private static CountDownLatch countDownLatch = new CountDownLatch(num);
    private static ExecutorService executorService = Executors.newFixedThreadPool(num);

    public static void main(String[] args) throws Exception {
        executorService.submit(() -> {
            System.out.println("A在上厕所");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("A上完了");
            }
        });

        executorService.submit(() -> {
            System.out.println("B在上厕所");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("B上完了");
            }
        });

        executorService.submit(() -> {
            System.out.println("C在上厕所");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("C上完了");
            }
        });
        System.out.println("等待所有人从厕所回来开会...");

        countDownLatch.await();

        System.out.println("所有人都好了，开始开会...");
        executorService.shutdown();
    }

}
