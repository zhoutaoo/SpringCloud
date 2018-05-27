package com.springboot.producer.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BusReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handleMessage(String message) {
        logger.info("Received <{}>", message);
    }

    @Bean
    MessageListenerAdapter mqListenerAdapter() {
        logger.info("new listener");
        return new MessageListenerAdapter(this);
    }

}
