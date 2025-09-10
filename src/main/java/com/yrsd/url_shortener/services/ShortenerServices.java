package com.yrsd.url_shortener.services;

import com.yrsd.url_shortener.dtos.requests.AddUrlRequest;
import com.yrsd.url_shortener.dtos.requests.FindUrlRequest;
import com.yrsd.url_shortener.dtos.responses.AddUrlResponse;
import com.yrsd.url_shortener.dtos.responses.FindUrlResponse;

public interface ShortenerServices {


    AddUrlResponse addUrl(AddUrlRequest request);

    FindUrlResponse findByShortUrl(FindUrlRequest request);
}
