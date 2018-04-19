package com.man.demo.jvm;

public class SynTest {
    public static void main(String[] args) {
        Integer i = 0;
        synchronized (i) {
            i++;
        }
        System.out.println(i);
    }
}
