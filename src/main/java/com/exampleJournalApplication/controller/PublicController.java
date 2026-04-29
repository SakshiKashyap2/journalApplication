package com.exampleJournalApplication.controller;

import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.repository.UserRepository;
import com.exampleJournalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }


    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        Boolean isUserCreated = userService.saveNewUser(user);
        if(!isUserCreated){
            return new ResponseEntity<>(isUserCreated, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(isUserCreated, HttpStatus.OK);
    }
}
