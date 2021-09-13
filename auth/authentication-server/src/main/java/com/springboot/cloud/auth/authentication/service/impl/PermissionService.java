package com.springboot.cloud.auth.authentication.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.springboot.cloud.auth.authentication.provider.ResourceProvider;
import com.springboot.cloud.auth.authentication.service.IPermissionService;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    ResourceProvider resourceProvider;

    /**
     * 权限的本地缓存列表
     */
    @CreateCache(name = "permission4Group" , localLimit = 50 ,cacheType = CacheType.LOCAL)
    @CachePenetrationProtect
    private Cache<String, Set<PermissionDTO>> permissionCache;



    @Override
    @Cached(name = "permission4Group::",key = "#groupCode",cacheType = CacheType.BOTH)
    //在一个JVM中，同一个键只有一个线程加载，其他线程等待结果。
    @CachePenetrationProtect
    public List<PermissionDTO> queryPermissionsByGroupCode(String groupCode) {
        return resourceProvider.permissions(groupCode).getData();
    }

    @Override
    public void savePermission(String groupCode, PermissionDTO permissionDTO) {
        Set<PermissionDTO> permissions = permissionCache.get(groupCode);
        if(CollectionUtils.isEmpty(permissions)){
            HashSet<PermissionDTO> set = new HashSet<>();
            set.add(permissionDTO);
            permissionCache.put(groupCode, set);
        }else{
            permissions.add(permissionDTO);
        }
    }

    @Override
    public void removePermission(String groupCode, PermissionDTO permissionDTO) {
        Set<PermissionDTO> permissions = permissionCache.get(groupCode);
        if(!CollectionUtils.isEmpty(permissions)){
            permissions.remove(permissionDTO);
        }
    }
}
