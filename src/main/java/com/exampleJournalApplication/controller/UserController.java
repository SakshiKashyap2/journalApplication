package com.exampleJournalApplication.controller;

import com.exampleJournalApplication.entity.JournalEntry;
import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<User>> getUserById(@PathVariable ObjectId id){
//        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);

        }


    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDB= userService.findByUserName(userName);
        if(userInDB!=null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
            return new ResponseEntity<>(userInDB, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

//    @PostMapping("/journal")
//    public ResponseEntity<?> addJournal (@RequestBody JsonNode body){
//        User userInDB= userService.findByUserName(body.get("userName").asString());
//        if(userInDB!=null){
//           if(userInDB.getPassword().equals(body.get("password").asString())){
//               JournalEntry newJournal= new JournalEntry("");
//               newJournal.setTitle(body.get("title").asString());
//               newJournal.setContent(body.get("content").asString());
//               newJournal.setDate(LocalDateTime.now());
//
//                  List<JournalEntry> listOfJournals=new ArrayList<>();
//            listOfJournals = userInDB.getJournalEntries();
//              listOfJournals.add(newJournal);
//
//               List<JournalEntry> listOfJournals = userInDB.getJournalEntries();
//               listOfJournals.add(newJournal);
//
//               userInDB.setJournalEntries(listOfJournals);
//               userService.saveUser(userInDB);
//               return new ResponseEntity<>(userInDB,HttpStatus.OK);
//
//           }
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//    }

//    @GetMapping("/journal")
//    public ResponseEntity<?> getAllJournalOfUsers(@RequestBody User user){
//        User userInDb= userService.findByUserName(user.getUserName());
//        if(userInDb.getUserName().equals(user.getUserName()) && userInDb.getPassword().equals(user.getPassword())){
//            List<JournalEntry> listOfJournals = userInDb.getJournalEntries();
//            return new ResponseEntity<>(listOfJournals,HttpStatus.OK);
//
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//    }

//    @PatchMapping("/journal")
//    public ResponseEntity<?> getContentByTitle(@RequestBody JsonNode body){
//        User userInDb= userService.findByUserName(body.get("userName").asString());
//        if(userInDb.getUserName().equals(body.get("userName").asString()) && userInDb.getPassword().equals(body.get("password").asString())){
//            List<JournalEntry> listOfJournals = userInDb.getJournalEntries();
//            for(int i=0;i<listOfJournals.size();i++){
//                if(listOfJournals.get(i).getTitle().equals(body.get("title").asString())){
//                    return new ResponseEntity<>(listOfJournals.get(i),HttpStatus.OK);
//
//                }
//
//            }
//
//        } return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }

//    @PutMapping("/journal")
//        public ResponseEntity<?> updateContent(@RequestBody JsonNode body){
//            User userInDb= userService.findByUserName(body.get("userName").asString());
//            if(userInDb.getUserName().equals(body.get("userName").asString()) && userInDb.getPassword().equals(body.get("password").asString())){
//                List<JournalEntry> listOfJournals = userInDb.getJournalEntries();
//                for(int i=0;i<listOfJournals.size();i++){
//                    if(listOfJournals.get(i).getTitle().equals(body.get("title").asString())){
//                       listOfJournals.get(i).setContent(body.get("content").asString());
//                       userInDb.setJournalEntries(listOfJournals);
//                       userService.saveUser(userInDb);
//                        return new ResponseEntity<>(listOfJournals.get(i),HttpStatus.OK);
//
//
//                    }
//
//                }
//            } return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }




    }





