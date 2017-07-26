package com.springboot.producer.rest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by zhoutaoo on 21/07/2017.
 */
@RestController
@RefreshScope
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${username}")
    private String username;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/users")
    @ApiOperation(value = "测试接口", notes = "测试接口详细描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    public String users() throws InterruptedException {
        int millis = new Random().nextInt(1000);
        logger.info("耗时：" + millis);
        Thread.sleep(millis);
        return discoveryClient.getServices().toString();
    }

    @RequestMapping("/user")
    public String user() throws InterruptedException {
        int millis = new Random().nextInt(1000);
        logger.info("耗时：" + millis);
        Thread.sleep(millis);
        return "username:" + username;
    }
}
