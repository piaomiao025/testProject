package com.man.demo.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-15 14:13
 **/
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        ExecutorService service = Executors.newFixedThreadPool(15);

        for (int i = 0 ; i < 5 ; i++) {
            service.submit(new Producer(list, 8));
        }

        for (int i = 0 ; i < 10 ; i++) {
            service.submit(new Consumer(list));
        }
    }

    static class Producer implements Runnable {
        private List<Integer> list;
        private int maxLengh;

        public Producer(List list, int maxLengh) {
            this.list = list;
            this.maxLengh = maxLengh;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.size() == maxLengh) {
                            System.out.println("生产者 " + Thread.currentThread().getName() + " list已达到最大容量，进行wait");
                            list.wait();
                            System.out.println("生产者 " + Thread.currentThread().getName() + " 退出wait");
                        }
                        Random random = new Random();
                        int i = random.nextInt();
                        System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据 " + i);
                        list.add(i);
                        list.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private List<Integer> list;
        public Consumer(List list) {
            this.list = list;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (list) {
                    try {
                        while (list.isEmpty()) {
                            System.out.println("消费者 " + Thread.currentThread().getName() + " list为空，需要wait");
                            list.wait();
                            System.out.println("消费者 " + Thread.currentThread().getName() + " 退出wait");
                        }
                        Integer element = list.remove(0);
                        System.out.println("消费者 " + Thread.currentThread().getName() + " 消费数据：" + element);
                        list.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
