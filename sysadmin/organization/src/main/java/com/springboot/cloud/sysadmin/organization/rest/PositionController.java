package com.springboot.cloud.sysadmin.organization.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.sysadmin.organization.entity.form.PositionForm;
import com.springboot.cloud.sysadmin.organization.entity.param.PositionQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Position;
import com.springboot.cloud.sysadmin.organization.service.IPositionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/position")
@Api("position")
@Slf4j
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "新增职位", notes = "新增一个职位")
    @ApiImplicitParam(name = "positionForm", value = "新增职位form表单", required = true, dataType = "PositionForm")
    @PostMapping
    public Result add(@Valid @RequestBody PositionForm positionForm) {
        log.debug("name:{}", positionForm);
        Position position = positionForm.toPo(Position.class);
        return Result.success(positionService.add(position));
    }

    @ApiOperation(value = "删除职位", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "职位ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(positionService.delete(id));
    }

    @ApiOperation(value = "修改职位", notes = "修改指定职位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "职位ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "positionForm", value = "职位实体", required = true, dataType = "PositionForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody PositionForm positionForm) {
        Position position = positionForm.toPo(Position.class);
        position.setId(id);
        return Result.success(positionService.update(position));
    }

    @ApiOperation(value = "获取职位", notes = "获取指定职位信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "职位ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id:{}", id);
        return Result.success(positionService.get(id));
    }

    @ApiOperation(value = "查询职位", notes = "根据条件查询职位信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "职位名称", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping
    public Result query(@RequestParam String name) {
        log.debug("query with name:{}", name);
        return Result.success(positionService.query(new PositionQueryParam(name)));
    }
}