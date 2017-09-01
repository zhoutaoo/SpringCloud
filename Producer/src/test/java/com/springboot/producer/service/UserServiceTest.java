package com.springboot.producer.service;

import com.springboot.producer.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void add() throws Exception {
        User user = new User("username", "passwd", "李四");
        user.setCreatedBy("system");
        user.setUpdatedBy("system");
        Assert.assertEquals(1, userService.add(user));
    }

}