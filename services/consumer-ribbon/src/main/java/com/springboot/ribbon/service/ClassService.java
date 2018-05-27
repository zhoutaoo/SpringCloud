package com.springboot.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.springboot.cloud.core.entity.Result;
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
    public Result users() {
        return restTemplate.getForEntity("http://EUREKA-PRODUCER/users/?name=张三", Result.class).getBody();
    }

    public Result usersFallback() {
        return Result.fail();
    }
}
