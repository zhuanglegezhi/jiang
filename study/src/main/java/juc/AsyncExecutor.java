package com.pdd.search.pes.v2.asyn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * Created by zz on 2021/3/29.
 * <p>
 * 异步处理工具类
 */
//@Component
public class AsyncExecutor {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);

    //    @Autowired
    private Executor taskExecutor;


    public <T> CompletableFuture<T> runWithTimeout(Supplier<T> supplier, long millis) {
        return within(CompletableFuture.supplyAsync(supplier, taskExecutor), millis);
    }

    private <T> CompletableFuture<T> within(CompletableFuture<T> future, long millis) {
        return failAfter(future, millis);
    }

    private <T> CompletableFuture<T> failAfter(CompletableFuture<T> future, long millis) {
        scheduler.schedule(() -> {
                    final TimeoutException ex = new TimeoutException("Timeout after " + millis + " millis");
                    return future.completeExceptionally(ex);
                },
                millis,
                TimeUnit.MILLISECONDS);
        return future;
    }

}
