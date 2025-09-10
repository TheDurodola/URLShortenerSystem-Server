package com.yrsd.url_shortener.services;

import com.yrsd.url_shortener.data.models.Link;
import com.yrsd.url_shortener.data.models.Links;
import com.yrsd.url_shortener.data.repositories.LinksRepo;
import com.yrsd.url_shortener.dtos.requests.AddUrlRequest;
import com.yrsd.url_shortener.dtos.requests.FindUrlRequest;
import com.yrsd.url_shortener.dtos.responses.AddUrlResponse;
import com.yrsd.url_shortener.dtos.responses.FindUrlResponse;
import com.yrsd.url_shortener.exceptions.UrlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.yrsd.url_shortener.utils.Mapper.map;
import static com.yrsd.url_shortener.utils.Mapper.mapToFindUrlResponse;

@Service
public class ShortenerServicesImpl implements ShortenerServices {

    @Autowired
    LinksRepo linksRepo;


    @Override
    public AddUrlResponse addUrl(AddUrlRequest request) {
        autoDeleteOldUrls();

        Links links = map(request);

        assignUrlDetails(links);

        linksRepo.save(links);
        return map(links);
    }


    @Override
    public FindUrlResponse findByShortUrl(FindUrlRequest request) {
        autoDeleteOldUrls();

        Link link = new Link();



        link.setUrl(request.getUrl());

        Optional<Links> found = linksRepo.findByShortUrl(request.getUrl());


        if (found.isPresent()) {

            return mapToFindUrlResponse(found.get());
        }
        else {
            throw new UrlNotFoundException();
        }

    }


    private void assignUrlDetails(Links links) {
        List<Links> listOfLinks = linksRepo.findAll();

        for (Links link : listOfLinks) {
            if (link.getOriginalUrl().equals(links.getOriginalUrl())) {
                links.setShortUrl(link.getShortUrl());
                links.setCreatedAt(link.getCreatedAt());
                linksRepo.delete(link);
                return;
              }
         }


        links.setShortUrl(UUID.randomUUID().toString().substring(0, 7));
        links.setCreatedAt(java.time.LocalDate.now());

    }
    private void autoDeleteOldUrls() {
        List<Links> listOfLinks = linksRepo.findAll();
        for (Links link : listOfLinks) {
            if (link.getCreatedAt().isBefore(java.time.LocalDate.now().minusDays(7))) {
                linksRepo.delete(link);
            }
        }
    }


}
