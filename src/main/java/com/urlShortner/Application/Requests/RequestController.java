package com.urlShortner.Application.Requests;

import com.urlShortner.Application.Requests.Request;
import com.urlShortner.Application.Requests.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RequestController {
    final long value = 1000L;

    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/Request")
    public ResponseEntity<List<Request>> getAllReqs(@RequestParam(required = false) String title) {
        try {
            List<Request> request = new ArrayList<Request>();

            requestRepository.findAll().forEach(request::add);

            if (request.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Request")
    public @ResponseBody
    ResponseEntity<Request> addNewRequest(@RequestParam Integer id, @RequestParam String request_ip, @RequestParam String request_referrer) {
        Request newReq = new Request();
        newReq.setUrlID(id);
        newReq.setRequestIP(request_ip);
        newReq.setRequestReferrer(request_referrer);
        newReq.setCreatedAt(System.currentTimeMillis() / value);
        try {
            Request _request = requestRepository.save(newReq);
            return new ResponseEntity<>(_request, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}