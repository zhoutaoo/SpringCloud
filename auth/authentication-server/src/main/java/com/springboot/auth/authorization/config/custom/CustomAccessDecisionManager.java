package com.springboot.oauth2.config.custom;

import com.springboot.oauth2.entity.Resource;
import com.springboot.oauth2.service.impl.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    ResourceService resourceService;

    /**
     * @param authentication
     * @param object           是url
     * @param configAttributes 权限集合
     * @throws AccessDeniedException 如果没有权限访问则抛出该异常
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
        log.debug("正在访问的url是:{}", object);

        if (configAttributes == null) {
            log.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - configAttributes is null");
            return;
        }
        //获取到用户角色所拥有的所有资源
        Set<Resource> resources = getResourcesByAuthorityRoles(authentication.getAuthorities());

        for (ConfigAttribute configAttribute : configAttributes) {
            //attribute为需要的权限数据
            String attribute = configAttribute.getAttribute();
            log.debug("请求url:{}, 需要的授权信息是:{}", object, attribute);

            for (Resource resource : resources) {
                //grantedAuthority 是用户的授权数据.
                if (attribute.equals(resource.getCode())) {
                    log.debug("url需要的授权资源信息是{},用户的授权资源是:{},授权数据相匹配", attribute, resource.getCode());
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限");
    }

    /**
     * 根据用户所被授予的角色，查询到用户所拥有的资源
     *
     * @param authorityRoles
     * @return
     */
    private Set<Resource> getResourcesByAuthorityRoles(Collection<? extends GrantedAuthority> authorityRoles) {
        //用户被授予的角色
        if (log.isDebugEnabled())
            log.debug("用户的授权角色集合信息为:{}", authorityRoles);

        List<String> authorityRoleCodes = authorityRoles.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
        Set<Resource> resources = resourceService.queryByRoleCodes((String[]) authorityRoleCodes.toArray());
        if (log.isDebugEnabled()) {
            log.debug("用户的授权角色的资源数量是:{}, 角色集合信息为:{}", resources.size(), resources);
        }
        return resources;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        log.debug("supports attribute:{}", attribute);
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        log.debug("supports attribute:{}", clazz);
        return true;
    }
}