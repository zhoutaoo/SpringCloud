package com.springboot.producer.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Receiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisConnectionFactory connectionFactory;

    public void handleMessage(String message) {
        logger.info("Received <" + message + ">");
    }

    @Bean
    @PostConstruct
    RedisMessageListenerContainer container() {
        logger.info("container init{}", connectionFactory);
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new Receiver());
        messageListenerAdapter.afterPropertiesSet();
        container.addMessageListener(messageListenerAdapter, new PatternTopic("chat"));
        return container;
    }
}