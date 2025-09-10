package com.yrsd.url_shortener.utils;

import com.yrsd.url_shortener.data.models.Links;
import com.yrsd.url_shortener.data.repositories.LinksRepo;
import com.yrsd.url_shortener.dtos.requests.AddUrlRequest;
import com.yrsd.url_shortener.dtos.responses.AddUrlResponse;
import com.yrsd.url_shortener.dtos.responses.FindUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.UUID;



public class Mapper {

    public static Links map(AddUrlRequest request) {
        Links links = new Links();
        links.setOriginalUrl(request.getOriginalUrl());
        return links;
    }


    public static AddUrlResponse map(Links links) {
        AddUrlResponse response = new AddUrlResponse();
        response.setOriginalUrl(links.getOriginalUrl());
        response.setShortUrl(links.getShortUrl());
        return response;
    }

    public static FindUrlResponse mapToFindUrlResponse(Links links) {

        FindUrlResponse response = new FindUrlResponse();
        response.setUrl(links.getOriginalUrl());
        return response;
    }

}
