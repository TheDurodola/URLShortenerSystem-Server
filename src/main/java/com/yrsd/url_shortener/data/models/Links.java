package com.yrsd.url_shortener.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;


@Document
@Data
public class Links {

    @Id
    private String id;


    private String originalUrl;
    private String shortUrl;
    private LocalDate createdAt;

}
