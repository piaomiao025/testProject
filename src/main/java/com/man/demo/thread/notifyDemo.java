package com.man.demo.thread;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-27 15:01
 **/
public class notifyDemo {
    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadDemo(), "t1");
        Thread t2 = new Thread(new ThreadDemo(), "t2");
        Thread t3 = new Thread(new ThreadDemo2(), "t3");
        t1.start();
        t2.start();

        try{
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();
    }

    static class ThreadDemo implements Runnable {
        @Override
        public void run() {
            try {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " 暂停前");
                    obj.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 恢复");
        }
    }

    static class ThreadDemo2 implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " 恢复前");
                obj.notify();
            }
            System.out.println(Thread.currentThread().getName() + " 恢复");
        }
    }
}
