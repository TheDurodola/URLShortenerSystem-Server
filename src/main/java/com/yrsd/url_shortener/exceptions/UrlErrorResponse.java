package com.yrsd.url_shortener.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlErrorResponse {

        private int status;
        private String message;
        private LocalDateTime timestamp;

        public UrlErrorResponse(int status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }



}
