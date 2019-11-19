package com.mc.p2p.controller;

import com.mc.p2p.common.RespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 谢星星
 * @Date: 2019/11/19 14:39
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test")
    public RespVo test() {
        redisTemplate.opsForValue().set("random", System.currentTimeMillis());
        Object obj = redisTemplate.opsForValue().get("random");
        return RespVo.SUCCESS(obj);
    }

}
