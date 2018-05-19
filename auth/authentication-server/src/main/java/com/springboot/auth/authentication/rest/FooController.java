package com.springboot.auth.authentication.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

@RestController
public class FooController {

    //@PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public String findById(@PathVariable long id) {
        return randomNumeric(2) + id;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public String deleteById(@PathVariable long id) {
        return randomNumeric(2) + id;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public String update(@PathVariable long id) {
        return randomNumeric(2) + id;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{id}")
    public String add(@PathVariable long id) {
        return randomNumeric(2) + id;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello/{name}")
    public String findByName(@PathVariable String name) {
        return randomNumeric(2) + name;
    }
}