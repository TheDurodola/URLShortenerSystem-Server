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
    public ResponseEntity<AddUrlResponse> shortenUrl(@RequestBody AddUrlRequest addUrlRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(shortenerServices.addUrl(addUrlRequest));
    }


    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<FindUrlResponse> getUrl(@PathVariable String shortenedUrl) {
        FindUrlRequest  findUrlRequest = new FindUrlRequest();
        findUrlRequest.setUrl(shortenedUrl);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(shortenerServices.findByShortUrl(findUrlRequest));
    }


}
