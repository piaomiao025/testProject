package com.man.demo.finalDemo;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-10-23 10:35
 **/
public class Demo {
    public static void main(String[] args) {
        System.out.println("开始执行");
        StaticInnerDemo.test();
        StaticInnerDemo.getInstance();
        StaticInnerDemo.getInstance();
        System.out.println("执行完成！");

        System.out.println("开始执行2");
        StaticInnerDemo2.test();
        StaticInnerDemo2.getInstance();
        StaticInnerDemo2.getInstance();
        System.out.println("2执行完成！");
    }
}
