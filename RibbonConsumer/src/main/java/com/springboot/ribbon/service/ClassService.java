package com.springboot.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhoutaoo on 23/07/2017.
 */
@Service
public class ClassService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "usersFallback")
    @CacheResult
    public String users() {
        String body = restTemplate.getForEntity("http://EUREKA-PRODUCER/users", String.class).getBody();
        return body;
    }

    private String usersFallback() {
        return "user fallback";
    }
}
