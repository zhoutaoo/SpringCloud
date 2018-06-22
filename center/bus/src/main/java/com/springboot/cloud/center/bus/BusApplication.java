package com.springboot.cloud.center.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BusApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class, args);
    }
}
