package com.man.demo.storm;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class WordReader implements IRichSpout {
    private static final long serialVersionID = 1L;
    private SpoutOutputCollector collector;
    private FileReader fileReader;
    private boolean completed = false;

    public boolean isDistributed() {
        return false;
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        try {
            this.fileReader = new FileReader(String.valueOf(conf.get("wordsFile")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error reading file [" + conf.get("wordsFile") + "]");
        }

        this.collector = collector;
    }

    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void nextTuple() {
        if(completed) {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {

            }

            return;
        }

        String str;
        BufferedReader reader = new BufferedReader(fileReader);
        try {
            while ((str = reader.readLine()) != null) {
                this.collector.emit(new Values(str), str);
            }
        } catch(IOException e) {
            throw new RuntimeException("Error reading tuple", e);
        } finally {
            completed = true;
        }
    }

    public void ack(Object msgId) {
        System.out.println("OK: " + msgId);
    }

    public void fail(Object msgId) {
        System.out.println("FAIL:" + msgId);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
