package com.redis.realtimesearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class searchController {

    private final RedisTemplate<String,String> redisTemplate;

    @PostMapping("/update")
    private String updateSearch(@RequestParam String keyword){
        redisTemplate.opsForZSet().incrementScore("popular_searches", keyword, 1);
        return "keyword updated " + keyword;
    }

    @GetMapping("/top")
    private Set<ZSetOperations.TypedTuple<String>> getTopSearches(){
        return redisTemplate.opsForZSet().reverseRangeWithScores("popular_searches", 0, 9);
    }
}
