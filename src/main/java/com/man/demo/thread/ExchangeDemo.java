package com.man.demo.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-15 09:50
 **/
public class ExchangeDemo {
    private static Exchanger exchanger = new Exchanger();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            try {
                String girl = exchanger.exchange("hello...").toString();
                System.out.println("girl said: " + girl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            try {
                System.out.println("girl out");
                TimeUnit.SECONDS.sleep(3);
                String boy = exchanger.exchange("hello too...").toString();
                System.out.println("boy said: " + boy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
