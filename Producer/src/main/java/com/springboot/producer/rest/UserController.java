package com.springboot.producer.rest;

import com.springboot.producer.entity.Result;
import com.springboot.producer.entity.User;
import com.springboot.producer.entity.form.UserAddForm;
import com.springboot.producer.entity.form.UserQueryForm;
import com.springboot.producer.entity.form.UserUpdateForm;
import com.springboot.producer.entity.param.UserQueryParam;
import com.springboot.producer.service.IUserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhoutaoo on 21/07/2017.
 */
@RestController
@RefreshScope
@RequestMapping("/users")
@Api(tags = "eureka-producer", description = "用户管理的API")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "userAddForm", value = "新增用户form表单", required = true, dataType = "UserAddForm")
    @RequestMapping(method = RequestMethod.POST)
    public Result<Long> add(@Valid @RequestBody UserAddForm userAddForm) {
        logger.info("name:", userAddForm);
        User user = new User(userAddForm.getUsername(), userAddForm.getPassword(), userAddForm.getName());
        user.setCreatedBy("system");
        user.setUpdatedBy("system");
        return Result.success(userService.add(user));
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable long id) {
        userService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改用户", notes = "修改指定用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "userUpdateForm", value = "用户实体", required = true, dataType = "UserUpdateForm")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable long id, @Valid @RequestBody UserUpdateForm userUpdateForm) {
        User user = new User();
        user.setId(id);
        user.setName(userUpdateForm.getName());
        userService.update(user);
        return Result.success();
    }

    @ApiOperation(value = "获取用户", notes = "获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<User> get(@PathVariable long id) {
        return Result.success(userService.get(id));
    }

    @ApiOperation(value = "查询用户", notes = "根据条件查询用户信息，简单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "用户姓名", required = true, dataType = "string")
    })
    @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<List<User>> query(@Valid @RequestParam String name) {
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setName(name);
        logger.info("name:", userQueryParam);
        return Result.success(userService.query(userQueryParam));
    }

    @ApiOperation(value = "搜索用户", notes = "根据条件查询用户信息")
    @ApiImplicitParam(name = "userQueryForm", value = "用户查询参数", required = true, dataType = "UserQueryForm")
    @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Result<List<User>> search(@Valid @RequestBody UserQueryForm userQueryForm) {
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setName(userQueryForm.getName());
        logger.info("name:", userQueryParam);
        return Result.success(userService.query(userQueryParam));
    }
}

