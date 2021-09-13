package com.springboot.cloud.auth.authentication.service.impl;

import com.springboot.cloud.auth.authentication.provider.ResourceProvider;
import com.springboot.cloud.auth.authentication.service.IGroupService;
import com.springboot.cloud.sysadmin.facade.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements IGroupService {

    @Qualifier("com.springboot.cloud.auth.authentication.provider.ResourceProvider")
    @Autowired
    ResourceProvider resourceProvider;

    @Override
    public List<GroupDTO> queryGroupsByUsername(String username) {
        return resourceProvider.groups(username).getData();
    }
}
