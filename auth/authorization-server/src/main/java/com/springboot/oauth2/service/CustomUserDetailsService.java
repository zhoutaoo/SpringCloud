package com.springboot.oauth2.service;

import com.springboot.oauth2.entity.Resource;
import com.springboot.oauth2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getByUsername(username);
        log.debug("loadByUsername:{}", user.toString());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                this.obtainGrantedAuthorities(user));
        return userDetails;
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param user
     * @return
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        Set<Resource> resources = resourceService.findUserResourcesByUserId(user.getId());
        log.debug("resources:{}", resources);
        Set<GrantedAuthority> authSet = resources.stream()
                .map(resource -> new SimpleGrantedAuthority(resource.getCode()))
                .collect(Collectors.toSet());
        return authSet;
    }
}
