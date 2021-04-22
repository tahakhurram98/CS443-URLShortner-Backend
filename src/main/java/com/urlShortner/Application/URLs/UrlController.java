package com.urlShortner.Application.URLs;

import com.urlShortner.Application.URLs.Url;
import com.urlShortner.Application.URLs.UrlRepository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    UrlRepository urlRepository;

    @GetMapping("/Url")
    public ResponseEntity<List<Url>> getAllTutorials(@RequestParam(required = false) String title) {
        return null;
    }


    @PostMapping("/Url")
    public ResponseEntity<Url> createTutorial(@RequestBody Url Uul) {
        return null;
    }

    @PutMapping("/Url/{id}")
    public ResponseEntity<Url> updateTutorial(@PathVariable("id") String id, @RequestBody Url user) {
        return null;
    }

    @DeleteMapping("/Url/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        return null;
    }

}