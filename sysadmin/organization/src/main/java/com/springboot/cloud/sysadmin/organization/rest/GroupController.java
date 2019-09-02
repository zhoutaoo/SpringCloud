package com.springboot.cloud.sysadmin.organization.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.sysadmin.organization.entity.form.GroupForm;
import com.springboot.cloud.sysadmin.organization.entity.form.GroupQueryForm;
import com.springboot.cloud.sysadmin.organization.entity.param.GroupQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Group;
import com.springboot.cloud.sysadmin.organization.service.IGroupService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
@Api("group")
@Slf4j
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @ApiOperation(value = "新增用户组", notes = "新增一个用户组")
    @ApiImplicitParam(name = "groupForm", value = "新增用户组form表单", required = true, dataType = "GroupForm")
    @PostMapping
    public Result add(@Valid @RequestBody GroupForm groupForm) {
        log.debug("name:{}", groupForm);
        return Result.success(groupService.add(groupForm.toPo(Group.class)));
    }

    @ApiOperation(value = "删除用户组", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户组ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(groupService.delete(id));
    }

    @ApiOperation(value = "修改用户组", notes = "修改指定用户组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户组ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "groupForm", value = "用户组实体", required = true, dataType = "GroupForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody GroupForm groupForm) {
        Group group = groupForm.toPo(Group.class);
        group.setId(id);
        return Result.success(groupService.update(group));
    }

    @ApiOperation(value = "获取用户组", notes = "获取指定用户组信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户组ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id:{}", id);
        return Result.success(groupService.get(id));
    }

    @ApiOperation(value = "查询用户组", notes = "根据条件查询用户组信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "用户组名称", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping
    public Result query(@RequestParam String name) {
        log.debug("query with name:{}", name);
        GroupQueryParam groupQueryParam = new GroupQueryParam();
        groupQueryParam.setName(name);
        return Result.success(groupService.query(groupQueryParam));
    }

    @ApiOperation(value = "搜索用户组", notes = "根据条件查询用户组信息")
    @ApiImplicitParam(name = "groupQueryForm", value = "用户组查询参数", required = true, dataType = "GroupQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody GroupQueryForm groupQueryForm) {
        log.debug("search with groupQueryForm:{}", groupQueryForm);
        return Result.success(groupService.query(groupQueryForm.toParam(GroupQueryParam.class)));
    }

    @ApiOperation(value = "根据父id查询用户组", notes = "根据父id查询用户组列表")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户组父ID", required = true, dataType = "long")
    @GetMapping(value = "/parent/{id}")
    public Result search(@PathVariable String id) {
        log.debug("query with parent id:{}", id);
        return Result.success(groupService.queryByParentId(id));
    }
}