package com.man.demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-14 10:27
 **/
public class AtomicIntegerDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }
}
