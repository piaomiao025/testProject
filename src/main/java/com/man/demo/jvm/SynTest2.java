package com.man.demo.jvm;

public class SynTest2 {
    public static void main(String[] args) {
//        String s1 = "aa";
//        String s2 = "bb";
//        String s3 = s1 + s2;
//        System.out.println(s3);

//        StringBuffer sb = new StringBuffer();
//        sb.append("aa").append("bb");
//        System.out.println(sb.toString());

        SynMethodTest st = new SynMethodTest();
        synchronized (st) {
            st.set("aa");
        }
        synchronized (st) {
            st.set("bb");
        }
        synchronized (st) {
            st.set("cc");
        }
        System.out.println(st.get());
    }

    static class SynMethodTest {
        String test;

        public void set(String test) {
            this.test = test;
        }

        public String get() {
            return this.test;
        }
    }
}
