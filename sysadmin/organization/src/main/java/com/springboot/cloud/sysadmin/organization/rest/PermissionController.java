package com.springboot.cloud.sysadmin.organization.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import com.springboot.cloud.sysadmin.organization.service.impl.PermissionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
@Api("permission")
@Slf4j
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @ApiOperation(value = "查询权限", notes = "根据groupCode查询用户所拥有的资源信息")
    @ApiImplicitParam(paramType = "path", name = "groupCode", value = "用户组Code", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/group")
    public Result queryByUsername(@RequestBody PermissionDTO permissionDTO) {
        log.debug("query with groupCode:{}", permissionDTO.getGroupCode());
        return Result.success(permissionService.queryByConditions(permissionDTO));
    }
}
