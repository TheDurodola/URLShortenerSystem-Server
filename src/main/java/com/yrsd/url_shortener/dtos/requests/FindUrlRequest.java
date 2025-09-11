package com.yrsd.url_shortener.dtos.requests;

import lombok.Data;

@Data
public class FindUrlRequest implements UrlRequest{
        private String url;


}
