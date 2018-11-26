package com.man.demo.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-14 15:51
 **/
public class CyclicBarrierDemo {
    private static CyclicBarrier barrier = new CyclicBarrier(6, () -> {
        System.out.println("所有运动员入场，裁判一声令下！");
    });

    public static void main(String[] args) {
        System.out.println("运动员入场！");

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0 ; i < 16 ; i++) {
            executorService.execute(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " 入场");
                    barrier.await();
                    System.out.println(threadName + " 出发");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

}
