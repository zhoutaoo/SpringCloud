package com.springboot.producer.task;

import com.springboot.producer.events.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTasks {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Sender sender;

    @Scheduled(fixedRate = 10000)
    public void sendMessageToRedis() {
        logger.info("Send Hello To Redis");
        sender.send("chat", "Hello from Redis!");
    }
}