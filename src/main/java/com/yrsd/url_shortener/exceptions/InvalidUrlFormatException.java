package com.yrsd.url_shortener.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUrlFormatException extends IllegalArgumentException{

    private static String message = "Invalid URL format";

    public InvalidUrlFormatException(String message) {
        super(message);
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }
}
