package com.springboot.producer.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BusSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static String QUEUE_NAME = "mq";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    Queue queue() {
        logger.info("queue");
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        logger.info("exchange");
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        logger.info("binding");
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
    }

    @Bean
    SimpleMessageListenerContainer mqContainer(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        logger.info("init container");
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    public void send(String routingKey, String message) {
        logger.info(routingKey + " => " + message);
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}
