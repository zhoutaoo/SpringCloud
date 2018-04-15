package com.springboot.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "eureka-producer", fallback = ClassServiceFallback.class)
public interface ClassService {

    @RequestMapping("/users/?name=张三")
    String users();
}
