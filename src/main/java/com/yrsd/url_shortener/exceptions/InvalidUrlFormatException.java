package com.yrsd.url_shortener.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUrlFormatException extends IllegalArgumentException{
    public InvalidUrlFormatException(String message) {
        super(message);
    }
}
