package com.redis.realtimesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchKeywordResponse {
    private String keyword;
    private String message;
}
