package com.man.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * User: MXQ
 * Date: 2018/1/18
 * Time: 17:35
 */
public class OOMObjectTest {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for(int i = 0 ; i < num ; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        list = null;
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        OOMObjectTest.fillHeap(1000);
    }
}
