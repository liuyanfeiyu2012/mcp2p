package com.mc.p2p;

import com.mc.p2p.infrastructure.constant.McConstant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(exclude = RedissonAutoConfiguration.class)
public class McP2PApplication {

    public static void main(String[] args) {
        new McConstant();
        SpringApplication.run(McP2PApplication.class, args);
    }
}
