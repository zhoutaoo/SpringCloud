package com.springboot.feign.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.feign.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping("/classes")
    public Result hello(@RequestParam String name) {
        return classService.users(name);
    }

}
