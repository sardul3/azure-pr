package com.sagar.azurepr.controller;

import com.sagar.azurepr.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UrlRedirectController {

    private final UrlService urlService;

    @Autowired
    public UrlRedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{key}")
    public void redirect(@PathVariable String key, HttpServletResponse response) throws IOException {
        response.sendRedirect(this.urlService.decodeKey(key).getOriginalUrl());
    }
}
