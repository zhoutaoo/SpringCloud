package com.springboot.auth.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Oauth2AuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthorizationApplication.class, args);
    }
}
