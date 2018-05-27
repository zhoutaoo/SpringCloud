package com.springboot.auth.authentication.service.impl;

import com.springboot.auth.authentication.entity.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceTest {
    @Autowired
    private ResourceService resourceService;

    @Test
    public void testFindAll_假如已配置有资源_当获取所有时_那么可以获取到所有资源集合() {
        Set<Resource> resources = resourceService.findAll();
        Assert.assertThat(resources.size(), greaterThan(2));
    }

    @Test
    public void testQueryByRoleCodes_假如存在角色ADMIN_当传入ADMIN时_那么可以获取到角色拥有的资源集合() {
        Set<Resource> resources = resourceService.queryByRoleCodes(new String[]{"ADMIN"});
        Assert.assertThat(resources.size(), greaterThan(2));
    }

    @Test
    public void testQueryByRoleCodes_假如不存在角色NOTHING_当传入NOTHING时_那么获取不到资源信息() {
        Set<Resource> resources = resourceService.queryByRoleCodes(new String[]{"NOTHING"});
        Assert.assertEquals(0, resources.size());
    }
}