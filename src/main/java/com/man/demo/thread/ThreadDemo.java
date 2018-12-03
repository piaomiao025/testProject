package com.man.demo.thread;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-28 14:56
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 中断了");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行完成");
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            t1.interrupt();
//            try{
//                t1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + " 执行完成");
        }, "t2");

        t1.start();
        t2.start();
    }
}
