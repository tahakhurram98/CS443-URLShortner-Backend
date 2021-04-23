package com.urlShortner.Application.URLs;

import com.urlShortner.Application.URLs.Url;
import com.urlShortner.Application.URLs.UrlRepository;

import com.urlShortner.Application.Utility.Generator;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

//    @GetMapping("/Url")
//    public ResponseEntity<List<Url>> getAllTutorials(@RequestParam(required = false) String title) {
//        return null;
//    }

    @PostMapping(path = "/Url")
    public @ResponseBody
    Url addNewURL(@RequestParam Integer user_id, @RequestParam String orig_url, @RequestParam String short_url,
                  @RequestParam long expires_at, @RequestParam int visitor_limit, @RequestParam int privateLink, HttpServletRequest request) {

//        boolean unauthorized = true;
//        if (user_id != null && user_id > 0)
//            unauthorized = false;

        if (orig_url.equals("") || orig_url.trim().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Original URL cannot be empty.");
        }

        Url new_url = new Url();
//        if(!unauthorized) {
            new_url.setUserID(user_id);
//        }
//        else {
//            new_url.setUserID(null);
//        }

        new_url.setOrigURL(orig_url);
        new_url.setCreatorIP(request.getRemoteAddr());
        boolean prvt = false;
        if(privateLink == 1) {
            prvt = true;
        }
        // If no custom URL is provided and mode is public, generate random short URL.
        if (prvt){
            if (!short_url.equals(""))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "You cannot choose short url for private URLs.");
            else {
                short_url = generateURL(12);
                new_url.setShortURL(short_url);
            }
        } else {
            if (!short_url.equals("")){
                if (short_url.trim().equals("")){
                    short_url = generateURL(5);
                    new_url.setShortURL(short_url);
                } else {
                    if (urlRepository.findByShortURL(short_url) != null) { // If random is already taken...
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "This URL is already taken.");
                    }
                    new_url.setShortURL(short_url);
                }
            } else {
                short_url = generateURL(5);
                new_url.setShortURL(short_url);
            }
        }

        new_url.setCreatedAt(System.currentTimeMillis() / 1000L);

        if (expires_at == 0){
            new_url.setExpiresAt((System.currentTimeMillis()/ 1000L) + 86400); // 24 hours from now on.
        } else {
            new_url.setExpiresAt(expires_at);
        }

        new_url.setVisitorCount(0);
//        if(!unauthorized)
            new_url.setVisitorLimit(visitor_limit);
//        else
//            new_url.setVisitorLimit(0);
        new_url.setCustom(prvt);

        try {
            new_url = urlRepository.save(new_url);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "The URL could not be registered");
        }

        return new_url;
    }

/////
    private String generateURL(int len) {
        String short_url = Generator.generateRandomString(len);
        if (urlRepository.findByShortURL(short_url) != null) { // If random is taken
            int attempt = 0;
            while (urlRepository.findByShortURL(short_url) != null) { //until you find one.
                if (attempt == 3) { //Try 3 times, if you dont find one yet, increase the length.
                    len++;
                    attempt = 0;
                }
                short_url = Generator.generateRandomString(len);
                attempt++;
            }
        }
        return short_url;
    }

}