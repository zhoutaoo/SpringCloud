package com.springboot.cloud.demos.producer.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mq")
@Slf4j
public class RabbitReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("Receiver: {}", message);
    }

}
