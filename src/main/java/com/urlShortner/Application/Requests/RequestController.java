package com.urlShortner.Application.Requests;

import com.urlShortner.Application.Requests.Request;
import com.urlShortner.Application.Requests.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/Request")
    public ResponseEntity<List<Request>> getAllTutorials(@RequestParam(required = false) String title) {
        return null;
    }


    @PostMapping("/Request")
    public ResponseEntity<Request> createTutorial(@RequestBody Request request) {
        return null;
    }

    @PutMapping("/Request/{id}")
    public ResponseEntity<Request> updateTutorial(@PathVariable("id") String id, @RequestBody Request request) {
        return null;
    }

    @DeleteMapping("/Request/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        return null;
    }

}