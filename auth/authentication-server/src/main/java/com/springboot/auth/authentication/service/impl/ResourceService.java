package com.springboot.auth.authentication.service.impl;

import com.springboot.auth.authentication.dao.ResourceMapper;
import com.springboot.auth.authentication.entity.Resource;
import com.springboot.auth.authentication.service.IResourceService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class ResourceService implements IResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Set<Resource> findAll() {
        return resourceMapper.findAll();
    }

    @Override
    public Set<Resource> queryByRoleCodes(String[] roleCodes) {
        if (ArrayUtils.isNotEmpty(roleCodes)) {
            return resourceMapper.queryByRoleCodes(roleCodes);
        }
        return Collections.emptySet();
    }
}
