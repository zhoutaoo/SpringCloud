package com.springboot.oauth2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
@Slf4j
public class CustomAccessDecisionManager implements AccessDecisionManager {

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
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while (ite.hasNext()) {
            ConfigAttribute configAttribute = ite.next();
            //attribute为需要的权限数据
            String attribute = configAttribute.getAttribute();
            log.debug("请求url:{}, 需要的授权信息是:{}", object, attribute);

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (log.isDebugEnabled()) {
                log.debug("用户的授权信息集合数量是:{}, 集合信息为:{}", authorities.size(), authorities);
            }

            for (GrantedAuthority grantedAuthority : authorities) {
                //grantedAuthority 是用户的授权数据.
                String authority = grantedAuthority.getAuthority();
                if (attribute.equals(authority)) {
                    log.debug("需要的授权信息是{},用户的授权是:{},授权数据相匹配", attribute, authority);
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限");
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