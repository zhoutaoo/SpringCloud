package com.springboot.cloud.auth.authentication.provider;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.sysadmin.organization.entity.po.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class ResourceProviderFallback implements ResourceProvider {
    @Override
    public Result<Set<Resource>> resources() {
        log.error("认证服务启动时加载资源异常！未加载到资源");
        return Result.fail();
    }

    @Override
    public Result<Set<Resource>> resources(String username) {
        log.error("认证服务查询用户异常！查询用户资源为空！");
        return Result.success(new HashSet<Resource>());
    }
}
