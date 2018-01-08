package com.man.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * User: MXQ
 * Date: 2018/1/8
 * Time: 14:55
 */
@Slf4j
@DisallowConcurrentExecution
public class TestTimer implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("timer started.");
        try{
            Thread.sleep(500);
        }catch(Exception e){

        }
        log.info("timer stopped.");
    }
}
