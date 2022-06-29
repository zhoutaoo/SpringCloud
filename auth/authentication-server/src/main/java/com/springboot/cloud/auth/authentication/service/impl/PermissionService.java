package com.springboot.cloud.auth.authentication.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.springboot.cloud.auth.authentication.provider.ResourceProvider;
import com.springboot.cloud.auth.authentication.service.IPermissionService;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Qualifier("com.springboot.cloud.auth.authentication.provider.ResourceProvider")
    @Autowired
    ResourceProvider resourceProvider;


    @Override
    @Cached(name = "permission4Group::", key = "#permissionDTO.groupCode", cacheType = CacheType.BOTH, expire = 5)
    //在一个JVM中，同一个键只有一个线程加载，其他线程等待结果。
//    @CachePenetrationProtect
    public List<PermissionDTO> queryPermissionsByGroupCode(PermissionDTO permissionDTO) {
        return resourceProvider.permissions(permissionDTO).getData();
    }

}
