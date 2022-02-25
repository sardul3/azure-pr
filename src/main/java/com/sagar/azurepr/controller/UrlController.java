package com.sagar.azurepr.controller;

import com.sagar.azurepr.model.Url;
import com.sagar.azurepr.service.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("create")
    public ResponseEntity<String> createShortUrl(@RequestParam("url") String longUrl) {
        Url url = this.urlService.saveUrlToDB(longUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(url.getKey());
    }

}