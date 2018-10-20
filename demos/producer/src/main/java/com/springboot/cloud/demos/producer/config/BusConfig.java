package com.springboot.cloud.demos.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BusConfig {

    public static final String QUEUE_NAME = "mq";
    public static final String EXCHANGE_NAME = "spring-boot-exchange";

    @Bean
    Queue queue() {
        log.info("queue name:{}", QUEUE_NAME);
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        log.info("exchange:{}", EXCHANGE_NAME);
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        log.info("binding {} to {} with {}", queue, exchange, QUEUE_NAME);
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
    }

    @Bean
    SimpleMessageListenerContainer mqContainer(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        log.info("init mqContainer");
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
