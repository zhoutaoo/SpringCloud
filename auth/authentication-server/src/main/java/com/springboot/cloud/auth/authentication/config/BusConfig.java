package com.springboot.cloud.auth.authentication.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.auth.authentication.events.ResourceBusReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BusConfig {

    private static final String EXCHANGE_NAME = "spring-boot-exchange";
    private static final String RESOURCE_ROUTING_KEY = "organization-resource";
    private static final String RESOURCE_QUEUE_SUFFIX = "resource";

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    Queue resourceQueue() {
        String queueName = new Base64UrlNamingStrategy(appName + ".").generateName() + RESOURCE_QUEUE_SUFFIX;
        log.info("resource queue name:{}", queueName);
        return new Queue(queueName, false);
    }


    /**
     * 交换机
     *
     * @return {@link TopicExchange}
     */
    @Bean
    TopicExchange exchange() {
        log.info("exchange:{}", EXCHANGE_NAME);
        return new TopicExchange(EXCHANGE_NAME);
    }


    /**
     * 绑定资源更新的队列
     *
     * @param queue    队列
     * @param exchange 交换机
     * @return {@link Binding}
     */
    @Bean
    Binding resourceBinding(@Qualifier("resourceQueue") Queue queue, TopicExchange exchange) {
        log.info("binding {} to {} with {}", queue, exchange, RESOURCE_ROUTING_KEY);
        return BindingBuilder.bind(queue).to(exchange).with(RESOURCE_ROUTING_KEY);
    }




    ////////////////////
    ////////////////////  资源更新相关配置
    ////////////////////
    @Bean
    SimpleMessageListenerContainer resourceMessageListenerContainer(ConnectionFactory connectionFactory, @Qualifier("resourceMessageListenerAdapter") MessageListenerAdapter messageListenerAdapter, @Qualifier("resourceQueue") Queue queue) {
        log.info("init resourceMessageListenerContainer {}", queue.getName());
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(queue.getName());
        container.setMessageListener(messageListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter resourceMessageListenerAdapter(ResourceBusReceiver resourceBusReceiver, @Qualifier("resourceMessageConverter") MessageConverter messageConverter) {
        log.info("new listener");
        return new MessageListenerAdapter(resourceBusReceiver, messageConverter);
    }

    @Bean
    public MessageConverter resourceMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }



}
