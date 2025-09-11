package com.yrsd.url_shortener.utils;

import com.yrsd.url_shortener.dtos.requests.AddUrlRequest;
import com.yrsd.url_shortener.dtos.requests.UrlRequest;
import com.yrsd.url_shortener.exceptions.InvalidUrlFormatException;

import java.util.regex.Pattern;

public class Validator {

    public static void validateUrl(UrlRequest request) {
        String url = request.getUrl();

        if(url == null || url.isBlank()) {
            throw new InvalidUrlFormatException("Url cannot be empty");
        }

        String regex = "^(https?://).*";
        Pattern pattern = Pattern.compile(regex);

        if(!pattern.matcher(url).matches()) {
            throw new InvalidUrlFormatException("Url is not valid");
        }

        regex = "^https?://$";
        pattern = Pattern.compile(regex);

        if(pattern.matcher(url).matches()) {
            throw new InvalidUrlFormatException("Url is not valid");
        }



        if (url.contains(" ")){
            throw new InvalidUrlFormatException("Url cannot contain spaces");
        }

        if (url.contains("\\")){
            throw new InvalidUrlFormatException("Url cannot contain backslashes");
        }



    }
}
