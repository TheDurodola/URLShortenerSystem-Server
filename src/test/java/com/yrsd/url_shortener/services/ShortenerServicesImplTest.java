package com.yrsd.url_shortener.services;

import com.yrsd.url_shortener.data.models.Links;
import com.yrsd.url_shortener.data.repositories.LinksRepo;
import com.yrsd.url_shortener.dtos.requests.AddUrlRequest;
import com.yrsd.url_shortener.dtos.requests.FindUrlRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ShortenerServicesImplTest {


    @Autowired
    ShortenerServices shortenerServices;

    @Autowired
    LinksRepo repo;


    @BeforeEach
    void setUp(){
        repo.deleteAll();
    }

    @Test
    void addUrl_DbSizeIs1() {
        AddUrlRequest request = new AddUrlRequest();
        shortenerServices.addUrl(request);
        assertEquals(1, repo.count());
    }


    @Test
    void addUrl_StoreAndRetrieveUrl() {
        AddUrlRequest request = new AddUrlRequest();
        request.setOriginalUrl("https://mvnrepository.com/artifact/net.sourceforge.javydreamercsw/MySQL-Driver");
        var response = shortenerServices.addUrl(request);
        assertEquals(request.getOriginalUrl(), response.getOriginalUrl());
    }


    @Test
    void addUrl_ShortUrlIs7Chars() {
        AddUrlRequest request = new AddUrlRequest();
        request.setOriginalUrl("https://mvnrepository.com/artifact/net.sourceforge.javydreamercsw/MySQL-Driver");
        var response = shortenerServices.addUrl(request);
        assertNotNull(response.getShortUrl());
        assertEquals(7, response.getShortUrl().length());
    }

    @Test
    void ifUrlAlreadyExistInDb_ShortenedUrlRemainsTheSame() {
        AddUrlRequest request = new AddUrlRequest();
        request.setOriginalUrl("https://mvnrepository.com/artifact/net.sourceforge.javydreamercsw/MySQL-Driver");
        var response1 = shortenerServices.addUrl(request);
        var response2 = shortenerServices.addUrl(request);
        assertEquals(1, repo.count());
        assertEquals(response1.getShortUrl(), response2.getShortUrl());
    }

    @Test
    void ifLinkExceeds_LifeCycleDuration_LinkIsRemovedFromDB() {
        Links link = new Links();
        link.setOriginalUrl("https://mvnrepository.com/artifact/net.sourceforge.javydreamercsw/MySQL-Driver");
        link.setCreatedAt(java.time.LocalDate.now().minusDays(8));
        link.setShortUrl("abcdefg");
        repo.save(link);
        assertEquals(1, repo.count());
        AddUrlRequest request = new AddUrlRequest();
        request.setOriginalUrl("https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html#findAllById(java.lang.Iterable)");
        shortenerServices.addUrl(request);
        assertEquals(1, repo.count());
    }

//    @Test
//    void Url_withImporperFormat_ThrowsException(){
//        AddUrlRequest request = new AddUrlRequest();
//        request.setOriginalUrl("htps:/mvnrepository.com/artifact/net.sourceforge.javydreamercsw/MySQL-Driver");
//        assertThrows(IllegalArgumentException.class,()-> shortenerServices.addUrl(request));
//    }

    @Test
    void findByShortUrl_RetrieveOriginalUrl() {
        AddUrlRequest request = new AddUrlRequest();
        request.setOriginalUrl("https://mvnrepository.com/artifact/net.sourceforge.javydreamercsw/MySQL-Driver");
        var response = shortenerServices.addUrl(request);

        FindUrlRequest findRequest = new FindUrlRequest();
        findRequest.setUrl(response.getShortUrl());
        var findResponse = shortenerServices.findByShortUrl(findRequest);
        assertEquals(request.getOriginalUrl(), findResponse.getUrl());
    }





}