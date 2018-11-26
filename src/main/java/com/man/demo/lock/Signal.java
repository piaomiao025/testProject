package com.man.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-08 16:20
 **/
//public class Signal {
//    public static void main(String[] args) {
//        ReentrantLock lock = AwaitSignal.lock;
//        Condition condition = AwaitSignal.condition;
//
//        lock.lock();
//
//        try {
//            condition.signalAll();
//        } finally {
//            lock.unlock();
//        }
//    }
//}
