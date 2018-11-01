package com.man.demo.finalDemo;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-10-22 10:42
 **/
public class FinalDemo {
    private int a;
    private final int b;
    private static FinalDemo finalDemo;

    public FinalDemo() {
        a = 1;
        b = 2;
    }

    public static void writer() {
        finalDemo = new FinalDemo();
    }

    public static void reader() {
        FinalDemo demo = finalDemo;
        int a = demo.a;
        int b = demo.b;
    }
}
