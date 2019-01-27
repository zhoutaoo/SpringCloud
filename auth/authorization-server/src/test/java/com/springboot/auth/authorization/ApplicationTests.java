package com.springboot.auth.authorization;

import com.springboot.auth.authorization.exception.AuthErrorType;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class ApplicationTests {

    @Test
    public void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("test_secret"));
    }

    @Test
    public void testMethod() {
        System.out.println(AuthErrorType.valueOf("SYSTEM_NO_PERMISSION").getCode());
    }
}
