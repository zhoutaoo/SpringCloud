package com.springboot.cloud.demos.producer.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusReceiver {

    public void handleMessage(String message) {
        log.info("Received Message:<{}>", message);
    }

    @Bean
    MessageListenerAdapter mqListenerAdapter() {
        log.info("new listener");
        return new MessageListenerAdapter(this);
    }

}
