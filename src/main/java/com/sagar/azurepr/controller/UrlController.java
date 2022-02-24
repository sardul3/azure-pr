package com.sagar.azurepr.controller;

import com.sagar.azurepr.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("create")
    public ResponseEntity<String> createShortUrl(@PathVariable String longUrl) {
        String shortUrlKey = this.urlService.createShortUrlKey(longUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrlKey);
    }
}