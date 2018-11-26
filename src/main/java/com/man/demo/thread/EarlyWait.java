package com.man.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-15 11:00
 **/
public class EarlyWait {
    private static List<String> lockObject = new ArrayList<>();

    public static void main(String[] args) {
        WaitThread waitThread = new WaitThread(lockObject);
        WaitThread waitThread2 = new WaitThread(lockObject);
        NotifyThread notifyThread = new NotifyThread(lockObject);

//        notifyThread.start();
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        waitThread.start();
        waitThread2.start();
        notifyThread.start();
    }

    static class WaitThread extends Thread {
        private List<String> lock;

        public WaitThread(List<String> lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    while (lock.isEmpty()) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + " 进到代码块");
                        System.out.println(threadName + " 开始wait");
                        lock.wait();
                        System.out.println(threadName + " 结束wait");
                    }
                    String element = lock.remove(0);
                    System.out.println("the first element is " + element);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class NotifyThread extends Thread {
        private List<String> lock;

        public NotifyThread(List<String> lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " 进入代码块");
                System.out.println(threadName + " 开始notify");
                lock.add("nofity");
                lock.notifyAll();
                System.out.println(threadName + " 结束notify");
            }
        }
    }
}
