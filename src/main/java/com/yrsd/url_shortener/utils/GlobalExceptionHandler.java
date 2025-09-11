package com.yrsd.url_shortener.utils;

import com.yrsd.url_shortener.exceptions.InvalidUrlFormatException;
import com.yrsd.url_shortener.exceptions.UrlErrorResponse;
import com.yrsd.url_shortener.exceptions.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUrlFormatException.class)
    public ResponseEntity<UrlErrorResponse> handleInvalidUrlFormat(InvalidUrlFormatException e) {
        UrlErrorResponse error = new UrlErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<UrlErrorResponse> handleUrlNotFound(UrlNotFoundException e) {
        UrlErrorResponse error = new UrlErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
