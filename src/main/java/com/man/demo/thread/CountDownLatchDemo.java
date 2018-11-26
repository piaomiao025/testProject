package com.man.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-14 11:14
 **/
public class CountDownLatchDemo {
    private static CountDownLatch startSignal = new CountDownLatch(1);

    private static CountDownLatch endSignal = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0 ; i < 16 ; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 运动员等待！！");
                    startSignal.await();
                    System.out.println(Thread.currentThread().getName() + " 冲刺！");
                    endSignal.countDown();
                    System.out.println(Thread.currentThread().getName() + " 到达终点！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("发号施令！");
        startSignal.countDown();
        endSignal.await();
        System.out.println("结束！");
        executorService.shutdown();
    }
}
