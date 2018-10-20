package com.springboot.cloud.demos.producer.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String routingKey, String message) {
        log.info("routingKey:{}=>message:{}", routingKey, message);
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}
