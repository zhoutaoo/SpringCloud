package com.springboot.ribbon.rest;

import com.springboot.cloud.core.entity.Result;
import com.springboot.ribbon.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhoutaoo on 21/07/2017.
 */
@RestController
public class ClassController {

    @Autowired
    ClassService classService;

    @RequestMapping("/classes")
    public Result hello() {
        return classService.users();
    }

}
