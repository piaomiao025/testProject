package com.man.demo.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User: MXQ
 * Date: 2018/1/22
 * Time: 10:04
 */
public class BTraceTest {
    public int add(int a, int b) {
        return a + b;
    }

    public void run() {
        int a = (int)(Math.random() * 1000);
        int b = (int)(Math.random() * 1000);
        System.out.println(add(a, b));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        BTraceTest test = new BTraceTest();
        bReader.readLine();
        for(int i = 0 ; i < 10 ; i++) {
            test.run();
        }
    }
}
