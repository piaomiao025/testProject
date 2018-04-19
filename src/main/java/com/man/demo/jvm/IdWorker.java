package com.man.demo.jvm;

import java.util.Calendar;
import java.util.Date;

public class IdWorker {
    private long workId;
    private long dcId;

    public IdWorker(Long dcId, Long workId) {
        if(workId >= this.maxWorkIdLengh || workId < 0) {
            throw new IllegalArgumentException("workId不合法");
        }
        if(dcId >= this.maxDcIdLengh || dcId < 0) {
            throw new IllegalArgumentException("dcId不合法");
        }
        this.workId = workId;
        this.dcId = dcId;
    }

    private long timeEpoch = 1288834974657L;
    private long sequenceLengh = 12L;
    private long workIdLengh = 5L;
    private long dcIdLengh = 5L;

    private long lastTimestamp;

    private long maxWorkIdLengh = -1L ^ (-1L << workIdLengh);

    private long maxDcIdLengh = -1L ^ (-1L << dcIdLengh);

    private long sequenceMask = -1L ^ (-1L << sequenceLengh);

    private long sequence = 0L;

    private long getNextTimestamp() {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public synchronized long getNextId() {
        long timeMill = System.currentTimeMillis();
        if(timeMill < lastTimestamp) {
            throw new RuntimeException("时间不合法");
        }
        if(timeMill == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if(sequence == 0L) {
                timeMill = getNextTimestamp();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timeMill;
        System.out.println("time: " + lastTimestamp);
        return (timeMill - timeEpoch) << (sequenceLengh + workIdLengh + dcIdLengh)
                | dcId << (dcIdLengh +sequenceLengh)
                | workId << sequenceLengh
                | sequence;

    }
}

class IdWorkerThread implements Runnable {
    private IdWorker idWorker;
    public IdWorkerThread(IdWorker idWorker) {
        this.idWorker = idWorker;
    }
    public void run() {
        if(idWorker == null) {
            throw new RuntimeException("idWorker没有初始化！");
        }
        while (true) {
            System.out.println("nextId: " + idWorker.getNextId());
        }
    }
}

class TestIdWorkder {
    public static void main(String[] args) throws Exception {
//        IdWorker id1 = new IdWorker(1L, 1L);
////        IdWorker id2 = new IdWorker(1L, 2L);
//        Thread t1 = new Thread(new IdWorkerThread(id1));
//        Thread t2 = new Thread(new IdWorkerThread(id1));
//        Thread t3 = new Thread(new IdWorkerThread(id1));
//        Thread t4 = new Thread(new IdWorkerThread(id1));
//        t1.setDaemon(true);
//        t2.setDaemon(true);
//        t3.setDaemon(true);
//        t4.setDaemon(true);
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//
//        Thread.sleep(2000);


        Calendar calendar = Calendar.getInstance();
        calendar.set(3030, 12, 31);

        System.out.println(calendar.getTimeInMillis());
        //‭4398046511103‬
        System.out.println(new Date(4398046511103L));
//        System.out.println(new Date(calendar.getTimeInMillis()));
    }
}