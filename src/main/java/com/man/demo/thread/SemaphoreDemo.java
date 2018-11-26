package com.man.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-15 09:42
 **/
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService services = Executors.newFixedThreadPool(10);

        for (int i = 0 ; i < 10 ; i++) {
            services.execute(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " 同学准备获取笔");
                    semaphore.acquire();
                    System.out.println(threadName + " 同学获取到笔");
                    System.out.println(threadName + "填写表格ing");
                    TimeUnit.SECONDS.sleep(3);
                    semaphore.release();
                    System.out.println(threadName + " 填写完表格，归还了笔");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        services.shutdown();
    }
}
