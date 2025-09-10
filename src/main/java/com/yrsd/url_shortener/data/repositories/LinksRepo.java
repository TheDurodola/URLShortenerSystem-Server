package com.yrsd.url_shortener.data.repositories;

import com.yrsd.url_shortener.data.models.Links;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface LinksRepo extends MongoRepository<Links, String> {
    Optional<Links> findByShortUrl(String shortUrl);
}
