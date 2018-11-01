package com.man.demo.finalDemo;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-10-23 10:32
 **/
public class StaticInnerDemo {
    public StaticInnerDemo() {
        System.out.println("初始化了");
    }

    private static class InnerInstance {
        static final StaticInnerDemo innerInstance = new StaticInnerDemo();
    }

    public static StaticInnerDemo getInstance() {
        return InnerInstance.innerInstance;
    }


    public static void test() {
        System.out.println("test2");
    }
}
