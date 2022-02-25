package com.sagar.azurepr.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.google.common.hash.Hashing;
import com.sagar.azurepr.exception.exceptions.UrlInvalidException;
import com.sagar.azurepr.exception.exceptions.UrlNotFoundException;
import com.sagar.azurepr.model.Url;
import com.sagar.azurepr.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
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

    public boolean isUrlValid(String urlString)  {
        boolean valid;
        try {
            URL url = new URL(urlString);
            url.toURI();
            valid = true;
        } catch (URISyntaxException | MalformedURLException e) {
            throw new UrlInvalidException("provided URL is invalid");
       } catch (Exception ec) {
            valid = false;
            throw new UrlInvalidException("provided URL is invalid");
        }
        return valid;
    }

    public Url saveUrlToDB(String originalUrl) {
        Url savedUrl = null;

        if(isUrlValid(originalUrl)) {
            String key = createShortUrlKey(originalUrl);
            Url url = Url.builder()
                    .id(UUID.randomUUID().toString())
                    .originalUrl(originalUrl)
                    .shortUrl(baseUrl+key)
                    .expiresAt(LocalDateTime.now().plusDays( 30 ))
                    .key(key)
                    .build();
            savedUrl = this.urlRepository.save(url);
        }
        return savedUrl;
    }

    public Url decodeKey(String key) {
        Url url = this.urlRepository.findByKey(key);
        if(url == null) {
            throw new UrlNotFoundException("Url not found");
        }
        return this.urlRepository.findByKey(key);
    }
}
