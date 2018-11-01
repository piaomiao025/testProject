package com.man.demo.finalDemo;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-10-23 10:37
 **/
public class StaticInnerDemo2 {
    private static StaticInnerDemo2 instance = new StaticInnerDemo2();

    public StaticInnerDemo2() {
        System.out.println("2初始化了！");
    }

    public static StaticInnerDemo2 getInstance() {
        return instance;
    }

    public static void test() {
        System.out.println("test2");
    }
}
