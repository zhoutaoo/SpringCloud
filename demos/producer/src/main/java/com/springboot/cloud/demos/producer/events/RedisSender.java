package com.springboot.cloud.demos.producer.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RedisSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic("chat"));
        logger.info("init container:{}", listenerAdapter);
        return container;
    }

    public void send(String channel, String message) {
        logger.info("{}=>{}", channel, message);
        stringRedisTemplate.convertAndSend(channel, message);
    }
}