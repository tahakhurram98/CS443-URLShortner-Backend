package com.urlShortner.Application.Users;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.urlShortner.Application.Requests.Request;
import com.urlShortner.Application.Users.User;
import com.urlShortner.Application.Users.UserRepository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    final long value = 1000L;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/User/Signup")
//    public @ResponseBody User addNewUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
    public @ResponseBody User addNewUser(@RequestBody User user) {
        System.out.println("HELLO");
//        System.out.println(user.getEmail());
//        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User();
        newUser.setId(Uuids.timeBased());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setCreatedAt(System.currentTimeMillis() / value);
        try {
            newUser = userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sign Up Failed!");
        }
        return newUser;
    }

    @GetMapping("/User")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String title) {
        try {
            List<User> user = new ArrayList<User>();

            userRepository.findAll().forEach(user::add);

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/User")
    public @ResponseBody
    User loginUser(@RequestParam String email, @RequestParam String password) {
        User temp = userRepository.findByEmail(email);
        //ObjectMapper mapper = new ObjectMapper();
        if (temp == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found.");
        } else {
            //if (BCrypt.checkpw(password, temp.getPassword())) {
            if(password.compareTo(temp.getPassword()) == 0){ //Comparing the provided password with the user's password
                return temp;
            } else
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid password.");
        }
    }
}