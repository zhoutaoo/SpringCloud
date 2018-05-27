package com.springboot.auth.authentication.dao;

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
public class ResourceMapperTest {

    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void testFindAll_假如已配置有资源_当获取所有时_那么可以获取到所有资源集合() {
        Set<Resource> resources = resourceMapper.findAll();
        Assert.assertThat(resources.size(), greaterThan(2));
    }

    @Test
    public void testQueryByRoleCodes_假如存在角色FIN_当传入1个角色_那么可以查询到这个角色拥有的资源() {
        Set<Resource> resources = resourceMapper.queryByRoleCodes(new String[]{"FIN"});
        Assert.assertThat(resources.size(), greaterThan(0));
    }

    @Test
    public void testQueryByRoleCodes_假如存在角色ADMIN和FIN_当传入这2个角色_那么可以查询到2个角色拥有的资源的并集() {
        Set<Resource> resources = resourceMapper.queryByRoleCodes(new String[]{"ADMIN", "FIN"});
        Assert.assertThat(resources.size(), greaterThan(2));
    }

    @Test
    public void testQueryByRoleCodes_假如不存在角色NOTHING_当传入角色NOTHING时_那么不能查询到角色拥有的资源() {
        Set<Resource> resources = resourceMapper.queryByRoleCodes(new String[]{"NOTHING"});
        Assert.assertEquals(0, resources.size());
    }

    @Test
    public void testQueryByRoleCodes_假如存在角色ADMIN且不存在角色NOTHING_当传入ADMIN时_那么可以查询到存在的角色拥有的资源() {
        Set<Resource> resources = resourceMapper.queryByRoleCodes(new String[]{"ADMIN", "NOTHING"});
        Assert.assertThat(resources.size(), greaterThan(2));
    }

}