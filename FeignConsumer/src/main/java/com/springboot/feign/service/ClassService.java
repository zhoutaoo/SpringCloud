package com.springboot.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "eureka-producer", fallback = ClassServiceFallback.class)
public interface ClassService {

    @RequestMapping("/users")
    String users();
}
