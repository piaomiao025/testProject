package com.man.demo.jvm;

/**
 * User: MXQ
 * Date: 2018/1/19
 * Time: 16:17
 */
public class JconsoleThreadTest {
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(true)
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    static class SynAddRunnable implements Runnable {
        int a, b;
        public SynAddRunnable(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        bf.readLine();
//        createBusyThread();;
//        bf.readLine();
//        Object obj = new Object();
//        createLockThread(obj);

        for(int i = 0 ; i < 100 ; i++) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }
}
