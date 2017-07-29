package com.springboot.producer.rest;

import com.springboot.producer.entity.Result;
import com.springboot.producer.entity.User;
import com.springboot.producer.entity.form.UserQueryForm;
import com.springboot.producer.entity.param.UserQueryParam;
import com.springboot.producer.service.IUserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhoutaoo on 21/07/2017.
 */
@RestController
@RefreshScope
@RequestMapping("/users")
@Api(tags = "eureka-producer", description = "用户管理的API")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${username}")
    private String username;

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    public Result<Long> add(@RequestBody User user) {
        logger.info("name:", user.getId());
        return Result.success(userService.add(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        userService.delete(id);
        return Result.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户", notes = "修改指定用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    })
    public Result update(@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return Result.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户", notes = "获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "long")
    public Result<User> get(@PathVariable long id) {
        return Result.success(userService.get(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户", notes = "根据条件查询用户信息")
    @ApiImplicitParam(name = "userQueryForm", value = "用户查询参数", required = true, dataType = "UserQueryForm")
    @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    public Result<List<User>> query(@RequestBody UserQueryForm userQueryForm) {
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setName(userQueryForm.getName());
        logger.info("name:", userQueryParam);
        return Result.success(userService.query(userQueryParam));
    }
}

