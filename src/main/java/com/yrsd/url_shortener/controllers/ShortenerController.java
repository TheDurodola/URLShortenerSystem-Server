package com.yrsd.url_shortener.controllers;

import com.yrsd.url_shortener.dtos.requests.AddUrlRequest;
import com.yrsd.url_shortener.dtos.requests.FindUrlRequest;
import com.yrsd.url_shortener.dtos.responses.AddUrlResponse;
import com.yrsd.url_shortener.dtos.responses.FindUrlResponse;
import com.yrsd.url_shortener.services.ShortenerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortenerController {

    @Autowired
    ShortenerServices shortenerServices;

    @PostMapping("/links")
    public ResponseEntity<?> shortenUrl(@RequestBody AddUrlRequest addUrlRequest) {

        AddUrlResponse response =shortenerServices.addUrl(addUrlRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<?> getUrl(@PathVariable String shortenedUrl) {
        FindUrlRequest  findUrlRequest = new FindUrlRequest();
        findUrlRequest.setUrl(shortenedUrl);

        FindUrlResponse response = shortenerServices.findByShortUrl(findUrlRequest);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(response);
    }


}
