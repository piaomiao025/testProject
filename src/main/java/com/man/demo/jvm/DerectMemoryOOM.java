package com.man.demo.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * User: MXQ
 * Date: 2018/1/11
 * Time: 17:03
 */
public class DerectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
