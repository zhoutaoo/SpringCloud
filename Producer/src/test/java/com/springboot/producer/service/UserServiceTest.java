package com.springboot.producer.service;

import com.springboot.producer.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void add() throws Exception {
        User user = new User();
        user.setName("王五");
        user.setUpdatedDate(new Date());
        userService.add(user);
    }

}