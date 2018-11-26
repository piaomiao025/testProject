package com.man.demo.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-08 17:25
 **/
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(Thread.currentThread());

    }
}
