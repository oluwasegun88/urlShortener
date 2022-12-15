package com.example.urlshortener.controller;

import com.example.urlshortener.AddUrlRequestDto;
import com.example.urlshortener.service.UrlShortner;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping("/url-shortner")
@AllArgsConstructor
public class UrlShortnerController extends HttpServlet {
    private final UrlShortner urlShortner;

    @PostMapping("/add")
    public ResponseEntity<?> addUrl(@RequestBody AddUrlRequestDto urlRequestDto){
        try {
            URL url =  new URL(urlRequestDto.getUrl());
            return ResponseEntity.ok().body(urlShortner.addUrl(url));
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }

    }

    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<?> redirectUrl(@PathVariable String shortUrl){
        URL url = urlShortner.redirect(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.toString())).build();

    }
}
