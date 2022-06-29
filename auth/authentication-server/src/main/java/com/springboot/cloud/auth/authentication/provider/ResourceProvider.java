package com.springboot.cloud.auth.authentication.provider;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.sysadmin.facade.dto.GroupDTO;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import com.springboot.cloud.sysadmin.organization.entity.po.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "organization", fallback = ResourceProviderFallback.class)
public interface ResourceProvider {

    @GetMapping(value = "/resource/all")
    Result<Set<Resource>> resources();

    @GetMapping(value = "/resource/user/{username}")
    Result<Set<Resource>> resources(@PathVariable("username") String username);

    @PostMapping(value = "/permission/group")
    Result<List<PermissionDTO>> permissions(@RequestBody PermissionDTO permissionDTO);

    @GetMapping(value = "/group/user/{username}")
    Result<List<GroupDTO>> groups(@PathVariable("username") String username);
}
