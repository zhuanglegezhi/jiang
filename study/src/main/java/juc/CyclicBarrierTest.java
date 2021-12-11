package juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zz on 2021/12/11.
 */
public class CyclicBarrierTest {
    private static int num = 3;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(num, ()
            -> {
        System.out.println("所有人都好了，开始开会...");
        System.out.println("-------------------");
    });
    private static ExecutorService executorService =
            Executors.newFixedThreadPool(num);

    public static void main(String[] args) throws Exception {
        executorService.submit(() -> {
            System.out.println("A在上厕所");
            try {
                Thread.sleep(4000);
                System.out.println("A上完了");

                cyclicBarrier.await();

                System.out.println("会议结束，A退出");
                System.out.println("所有人都好了，开始开会...");
                System.out.println("-------------------");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        });

        executorService.submit(() -> {
            System.out.println("B在上厕所");
            try {
                Thread.sleep(2000);
                System.out.println("B上完了");

                cyclicBarrier.await();

                System.out.println("会议结束，B退出");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        });

        executorService.submit(() -> {
            System.out.println("C在上厕所");
            try {
                Thread.sleep(3000);
                System.out.println("C上完了");

                cyclicBarrier.await();

                System.out.println("会议结束，C退出");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        });

        executorService.shutdown();
    }

}
