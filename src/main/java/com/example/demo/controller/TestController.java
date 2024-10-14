package com.example.demo.controller;

import com.example.demo.config.EnvironmentModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    EnvironmentModifier environmentModifier;

    @Autowired
    Environment environment;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/prop-update")
    public String getUpdatedProperty(@RequestParam String key){
        environmentModifier.setEnvironmentProperty("test.prop", key);
        return environment.getProperty("test.prop");
    }

    @GetMapping("/redis-test")
    public String redis(@RequestParam String key, @RequestParam String value){
        redisTemplate.opsForValue().set(key, value);
        System.out.println("Key saved into redis: " + key);
        String valueFromRedis = (String) redisTemplate.opsForValue().get(key);
        System.out.println("Value from redis: " + valueFromRedis);
        return valueFromRedis;
    }
}
