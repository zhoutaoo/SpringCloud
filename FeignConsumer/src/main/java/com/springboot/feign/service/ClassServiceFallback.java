package com.springboot.feign.service;

import org.springframework.stereotype.Component;

@Component
public class ClassServiceFallback implements ClassService {
    @Override
    public String users() {
        return "No Users";
    }
}
