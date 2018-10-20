package com.springboot.cloud.demos.ribbon.rest;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.demos.ribbon.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ClassController {

    @Autowired
    ClassService classService;

    @GetMapping("/classes")
    public Result hello(@RequestParam String name) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Result users = classService.users(name);
        context.close();
        return users;
    }

    @PostMapping("/classes")
    public Result hello(@RequestBody Map<String, String> params) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Result users = classService.users(params);
        context.close();
        return users;
    }

}
