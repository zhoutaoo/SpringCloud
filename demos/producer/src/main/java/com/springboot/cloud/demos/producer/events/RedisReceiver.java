package com.springboot.cloud.demos.producer.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RedisReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handleMessage(String message) {
        logger.info("Received <{}>", message);
    }

    @Bean
    MessageListenerAdapter redisListenerAdapter() {
        logger.info("new listener");
        return new MessageListenerAdapter(this);
    }
}