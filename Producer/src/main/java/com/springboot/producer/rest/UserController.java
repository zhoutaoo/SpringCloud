package com.springboot.producer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by zhoutaoo on 21/07/2017.
 */
@RestController
@RefreshScope
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${username}")
    private String username;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/users")
    public String users() throws InterruptedException {
        int millis = new Random().nextInt(2000);
        logger.info("耗时：" + millis);
        Thread.sleep(millis);
        return discoveryClient.getServices().toString();
    }

    @RequestMapping("/user")
    public String user() throws InterruptedException {
        int millis = new Random().nextInt(1000);
        logger.info("耗时：" + millis);
        Thread.sleep(millis);
        return "username:" + username;
    }
}
