package com.redis.realtimesearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class TestController {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public TestController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/check")
    public String checkRedisConnection() {
        try {
            // 테스트용 키-값 설정 및 확인
            String testKey = "redis-test-key";
            String testValue = "connected";

            redisTemplate.opsForValue().set(testKey, testValue);
            String value = redisTemplate.opsForValue().get(testKey);

            if (testValue.equals(value)) {
                return "Redis is connected successfully!";
            } else {
                return "Redis connection failed!";
            }
        } catch (Exception e) {
            return "Redis connection error: " + e.getMessage();
        }
    }
}

