package com.man.demo.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-15 16:02
 **/
public class BlockingQueueProducerConsumerDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0 ; i < 5 ; i++) {
            service.submit(new Producer(queue));
        }

        for (int i = 0 ; i < 10 ; i++) {
            service.submit(new Consumer(queue));
        }
    }

    static class Producer implements Runnable {
        private BlockingQueue<Integer> queue;
        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while(true) {
                try {
                    Random random = new Random();
                    int ele = random.nextInt();
                    queue.put(ele);
                    System.out.println("生产者 " + threadName + " 生产数据 " + ele);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while(true) {
                try {
                    int ele = queue.take();
                    System.out.println("消费者 " + threadName + " 消费数据： " + ele);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
