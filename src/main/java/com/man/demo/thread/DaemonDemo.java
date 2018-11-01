package com.man.demo.thread;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-01 10:46
 **/
public class DaemonDemo {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
          while (true) {
              try {
                  System.out.println("i am alive");
                  Thread.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } finally {
                  System.out.println("finally block");
              }
          }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
