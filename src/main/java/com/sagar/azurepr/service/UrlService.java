package com.sagar.azurepr.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UrlService {
    int order = 0;
    final String baseUrl = "www.shortly.com/";

    public String createShortUrlKey(String originalUrl) {
        order++;
        String encodedKey = Base64.getEncoder().encodeToString((originalUrl+1).getBytes(StandardCharsets.UTF_8));
        return baseUrl + encodedKey.substring(0,5);
    }

    public void deleteShortUrlKey(String shortUrlKey) {

    }
}
