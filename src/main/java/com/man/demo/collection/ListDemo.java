package com.man.demo.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-21 09:35
 **/
public class ListDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(4);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        System.out.println(list.size());
    }
}
