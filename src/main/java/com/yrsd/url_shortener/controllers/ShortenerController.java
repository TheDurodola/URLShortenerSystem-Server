package com.yrsd.url_shortener.controllers;

import com.yrsd.url_shortener.dtos.requests.AddUrlRequest;
import com.yrsd.url_shortener.dtos.responses.AddUrlResponse;
import com.yrsd.url_shortener.services.ShortenerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController {

    @Autowired
    ShortenerServices shortenerServices;

    @PostMapping("/urlshortner/links")
    public AddUrlResponse shortenUrl(@RequestBody AddUrlRequest addUrlRequest) {
        return shortenerServices.addUrl(addUrlRequest);
    }


}
