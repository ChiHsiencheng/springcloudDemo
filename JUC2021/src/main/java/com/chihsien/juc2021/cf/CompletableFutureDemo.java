package com.chihsien.juc2021.cf;


import java.util.concurrent.*;

/*** 
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                20,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("CompletableFuture默认线程池：" + Thread.currentThread().getName());
        });
        System.out.println(future1.get());

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("CompletableFuture自定义线程池：" + Thread.currentThread().getName());
        },threadPoolExecutor);
        System.out.println(future2.get());

        System.out.println("====================================");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture默认线程池：" + Thread.currentThread().getName());
            return Thread.currentThread().getName();
            });
        System.out.println(future3.get());

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture自定义线程池：" + Thread.currentThread().getName());
            return Thread.currentThread().getName();
            },threadPoolExecutor);
        System.out.println(future4.get());



        threadPoolExecutor.shutdown();
    }
}
