package com.mc.p2p;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = RedissonAutoConfiguration.class)
public class McP2PApplication {

    public static void main(String[] args) {
        SpringApplication.run(McP2PApplication.class, args);
    }
}
