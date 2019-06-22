package com.springboot.auth.authorization.provider;

import com.springboot.auth.authorization.entity.Role;
import com.springboot.auth.authorization.entity.User;
import com.springboot.cloud.common.core.entity.vo.Result;

import java.util.HashSet;
import java.util.Set;

public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Result<User> getUserByUsername(String username) {
        return Result.success(new User());
    }

    @Override
    public Result<Set<Role>> queryRolesByUserId(long userId) {
        return Result.success(new HashSet<Role>());
    }
}
