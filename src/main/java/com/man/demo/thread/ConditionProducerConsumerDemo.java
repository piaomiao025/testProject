package com.man.demo.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-15 15:37
 **/
public class ConditionProducerConsumerDemo {
    private static Lock lock = new ReentrantLock();
    private static Condition full = lock.newCondition();
    private static Condition empty = lock.newCondition();

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0 ; i < 5 ; i++) {
            service.submit(new Producer(8, list));
        }

        for (int i = 0 ; i < 10 ; i++) {
            service.submit(new Consumer(list));
        }
    }

    static class Producer implements Runnable {
        private int maxLength;
        private List<Integer> list;

        public Producer(int maxLength, List<Integer> list) {
            this.maxLength = maxLength;
            this.list = list;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (true) {
                try {
                    lock.lock();
                    while (list.size() == maxLength) {
                        System.out.println("生产者 " + threadName + " 达到最大，需要await");
                        full.await();
                        System.out.println("生产者 " + threadName + " 结束await");
                    }

                    Random random = new Random();
                    int ele = random.nextInt();
                    list.add(ele);
                    System.out.println("生产者 " + threadName + " 生产数据： " + ele);
                    empty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }

    static class Consumer implements Runnable {
        private List<Integer> list;

        public Consumer(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while(true) {
                lock.lock();
                try {
                    while (list.isEmpty()) {
                        System.out.println("列表为空，消费者 " + threadName + " 需要await");
                        empty.await();
                        System.out.println("消费者 " + threadName + " 结束await");
                    }

                    int ele = list.remove(0);
                    System.out.println("消费者 " + threadName + " 消费：" + ele);
                    full.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
