package com.man.demo.jvm;

public class Parent {
    public Parent() {
        System.out.println("父类构造方法初始化");
    }

    static {
        System.out.println("父类的静态代码块初始化");
    }

    public static int ti = 0;

    static {
        ti = 2;
        System.out.println("ti:" + ti);
    }
}
