package com.sagar.azurepr.service;

import com.google.common.hash.Hashing;
import com.sagar.azurepr.exception.exceptions.UrlNotFoundException;
import com.sagar.azurepr.model.Url;
import com.sagar.azurepr.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UrlService {

    private final UrlRepository urlRepository;

    final String baseUrl = "www.shortly.com/";

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String createShortUrlKey(String originalUrl) {
        return Hashing.murmur3_32()
                .hashString(originalUrl.concat(LocalDateTime.now().toString()),
                        StandardCharsets.UTF_8).toString();
    }

    public Url saveUrlToDB(String originalUrl) {
        String key = createShortUrlKey(originalUrl);
        Url url = Url.builder()
                .id(UUID.randomUUID().toString())
                .originalUrl(originalUrl)
                .shortUrl(baseUrl+key)
                .expiresAt(LocalDateTime.now().plusDays( 30 ))
                .key(key)
                .build();
        return this.urlRepository.save(url);
    }

    public Url decodeKey(String key) {
        Url url = this.urlRepository.findByKey(key);
        if(url == null) {
            throw new UrlNotFoundException("Url not found");
        }
        return this.urlRepository.findByKey(key);
    }
}
