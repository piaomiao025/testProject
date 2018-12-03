package com.man.demo;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-28 10:27
 **/
public class TreeMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> treeMap = new TreeMap<>((o1, o2) -> o2 - o1);
        treeMap.put(12, "a");
        treeMap.put(21, "c");
        treeMap.put(10, "b");

        for(Map.Entry entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
