package com.redis.realtimesearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Value("${redis.keys.popular_searches}")
    private String popularSearchesKey;

    private final RedisTemplate<String, String> redisTemplate;

    public void incrementSearchScore(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        redisTemplate.opsForZSet().incrementScore("popular_searches", keyword.trim(), 1);
        //redisTemplate.expire("popular_searches", Duration.ofDays(7)); // 7일 TTL 설정
    }

    public Set<ZSetOperations.TypedTuple<String>> getTopSearches() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(popularSearchesKey, 0, 9);
    }
}

