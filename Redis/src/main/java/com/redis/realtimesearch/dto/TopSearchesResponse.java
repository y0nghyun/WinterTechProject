package com.redis.realtimesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

@Getter
@AllArgsConstructor
public class TopSearchesResponse {
    private Set<ZSetOperations.TypedTuple<String>> topSearches;
}
