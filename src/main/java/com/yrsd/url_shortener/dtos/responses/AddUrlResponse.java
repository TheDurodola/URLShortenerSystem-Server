package com.yrsd.url_shortener.dtos.responses;


import lombok.Data;

@Data
public class AddUrlResponse {
    private String shortUrl;
    private String originalUrl;
}
