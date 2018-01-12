package com.man.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * User: MXQ
 * Date: 2018/1/9
 * Time: 11:04
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args){
//        List<String> list = new ArrayList<String>();
//        int i = 0;
//        while(true){
//            list.add(String.valueOf(i++).intern());
//        }

        String str1 = new StringBuffer("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuffer("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
