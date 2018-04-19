package com.man.demo.jvm;

public class Son extends Parent {
    public Son() {
        System.out.println("子类构造函数初始化");
    }

    public static int tj = 0;
    static {
        System.out.println("子类静态代码块初始化");
        System.out.println("tj: " + tj);
    }

    public static void main(String[] args) {
        Son son = new Son();
    }
}
