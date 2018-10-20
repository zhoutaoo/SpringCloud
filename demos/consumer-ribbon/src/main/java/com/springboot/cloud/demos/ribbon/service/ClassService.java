package com.springboot.cloud.demos.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.ErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ClassService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "usersFallback")
    @CacheResult
    public Result users(String name) {
        return restTemplate.getForEntity("http://PRODUCER/product/?name={1}", Result.class, name).getBody();
    }

    @HystrixCommand(fallbackMethod = "usersFallback")
    @CacheResult
    public Result users(Map map) {
        return restTemplate.postForEntity("http://PRODUCER/product/", map, Result.class).getBody();
    }

    public Result usersFallback(String name) {
        return Result.fail(ErrorType.SYSTEM_BUSY);
    }

    public Result usersFallback(Map map) {
        return Result.fail(ErrorType.SYSTEM_BUSY);
    }
}
