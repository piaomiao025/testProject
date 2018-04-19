package com.man.demo.jvm;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static AtomicInteger race = new AtomicInteger(0);

    public static volatile int race2 = 0;

    public static void increase() {
//        race.incrementAndGet();
        race2++;
    }

    private static final int THREDS_COUNT = 20;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[THREDS_COUNT];
        for(int i = 0 ; i < THREDS_COUNT ; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for(int i = 0 ; i < 10000 ; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        long end = System.currentTimeMillis();

        System.out.println("时间：" + (end - start));
        System.out.println("时间：" + end);
        System.out.println(race2);
    }
}
