package com.springboot.feign.rest;

import com.springboot.feign.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping("/classes")
    public String hello() {
        return classService.users();
    }

}
