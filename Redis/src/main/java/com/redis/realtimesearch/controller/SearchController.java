package com.redis.realtimesearch.controller;

import com.redis.realtimesearch.dto.SearchKeywordResponse;
import com.redis.realtimesearch.dto.TopSearchesResponse;
import com.redis.realtimesearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/update")
    public ResponseEntity<SearchKeywordResponse> updateSearchKeyword(@RequestParam String keyword) {
        searchService.incrementSearchScore(keyword);
        SearchKeywordResponse response = new SearchKeywordResponse(keyword, "Keyword updated successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top")
    public ResponseEntity<TopSearchesResponse> getTopSearches() {
        TopSearchesResponse response = new TopSearchesResponse(searchService.getTopSearches());
        return ResponseEntity.ok(response);
    }
}
