package com.urlShortner.Application.Users;

import com.urlShortner.Application.Users.User;
import com.urlShortner.Application.Users.UserRepository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/Users")
    public ResponseEntity<List<User>> getAllTutorials(@RequestParam(required = false) String title) {
        return null;
    }


    @PostMapping("/Users")
    public ResponseEntity<User> createTutorial(@RequestBody User user) {
        return null;
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<User> updateTutorial(@PathVariable("id") String id, @RequestBody User user) {
        return null;
    }

    @DeleteMapping("/Users/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        return null;
    }

}