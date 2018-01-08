package com.man.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QuartzManager {

    private final static String JOB_GROUP_NAME = "QUARTZ_JOBGROUP_NAME";//任务组
    private final static String TRIGGER_GROUP_NAME = "QUARTZ_TRIGGERGROUP_NAME";//触发器组
    private static Scheduler scheduler = null;

    static {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private QuartzManager() {
    }

    public static void start() {
        try {

            if (scheduler.isStarted()) {
                return;
            }
            scheduler.start();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        try {
            if (scheduler.isShutdown()) {
                return;
            }
            scheduler.shutdown();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void addSimpleJob(Class<? extends Job> jobClass, String name,
                                    String triggerName, int second, Map map) {

        //Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
        // Date startTime1 = DateBuilder.futureDate(10,
        // DateBuilder.IntervalUnit.SECOND);

        JobDetail job = JobBuilder.newJob(jobClass)
                .withIdentity(name, JOB_GROUP_NAME).build();

        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, TRIGGER_GROUP_NAME).startNow()
                        //.startAt(startTime)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(second).repeatForever())
                .build();
        if (map != null) {
            job.getJobDataMap().putAll(map);
        }

        try {
            Date date = scheduler.scheduleJob(job, trigger);
            log.info("jobClass={},date={}", jobClass.getName(), date.getTime());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public static void addJob(Class<? extends Job> jobClass, String name,
                              String triggerName, String cron, Map map) {

        //Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
        // Date startTime1 = DateBuilder.futureDate(10,
        // DateBuilder.IntervalUnit.SECOND);

        JobDetail job = JobBuilder.newJob(jobClass)
                .withIdentity(name, JOB_GROUP_NAME).build();

        Trigger trigger = (CronTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, TRIGGER_GROUP_NAME)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(cron))
                .build();
        if (map != null) {
            job.getJobDataMap().putAll(map);
        }
        try {
            Date date = scheduler.scheduleJob(job, trigger);
            log.info("jobClass={},date={}", jobClass.getName(), date.getTime());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        TestTimer testTimer =  new TestTimer();
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("testTimer", testTimer);
		QuartzManager.addJob(TestTimer.class, "asd", "dd", "*/7 * * * * ?", map);
		QuartzManager.start();
    }

}

