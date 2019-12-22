package com.springboot.auth.authorization.provider;

import com.springboot.auth.authorization.entity.Role;
import com.springboot.auth.authorization.entity.User;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "organization", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {

    @GetMapping(value = "/user")
    Result<User> getUserByUniqueId(@RequestParam("uniqueId") String uniqueId);

    @GetMapping(value = "/role/user/{userId}")
    Result<Set<Role>> queryRolesByUserId(@PathVariable("userId") String userId);
}
