package com.yrsd.url_shortener.dtos.requests;

import lombok.Data;

@Data
public class AddUrlRequest implements UrlRequest {
    String Url;
}
