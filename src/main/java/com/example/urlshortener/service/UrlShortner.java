package com.example.urlshortener.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlShortner {
    private final Map<String, URL> urlMap;
    public UrlShortner(){
        urlMap = new HashMap<>();
    }

    public String addUrl(URL longUrl){
        String shortUrl=generateShortUrl(longUrl);
        urlMap.put(shortUrl,longUrl);
        return  shortUrl;
    }

    private String generateShortUrl(URL longUrl){
        return Integer.toString(longUrl.hashCode());
    }


    public URL redirect(String shortUrl) {
        return urlMap.get(shortUrl);
    }


}
