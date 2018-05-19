package com.springboot.turbine;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;


public class ConsumerApplicationTests {

    @Test
    public void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("test_secret"));
    }

    @Test
    public void testMethod() {
        List<SimpleGrantedAuthority> authorities;
        SimpleGrantedAuthority admin = new SimpleGrantedAuthority("ADMIN");
        SimpleGrantedAuthority user = new SimpleGrantedAuthority("USER");
        authorities = Lists.newArrayList(admin, user);
        authorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
    }

}
