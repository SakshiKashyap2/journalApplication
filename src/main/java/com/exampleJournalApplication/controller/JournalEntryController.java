package com.exampleJournalApplication.controller;

import com.exampleJournalApplication.entity.JournalEntry;
import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.service.JournalEntryService;
import com.exampleJournalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/journal")
    public class JournalEntryController {

        @Autowired
        private JournalEntryService journalEntryService;

        @Autowired
        private UserService userService;

        @GetMapping("{userName}")
        public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
            User user=userService.findByUserName(userName);
            List<JournalEntry>all=user.getJournalEntries();
            if (all!=null && !all.isEmpty()) {
                return new ResponseEntity<>(all,HttpStatus.OK);
            }
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        @PostMapping("{userName}")
        public ResponseEntity<?>createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {

            try{
                User user=userService.findByUserName(userName);
                myEntry.setDate(LocalDateTime.now());
                journalEntryService.saveEntry(myEntry,userName);
                return new ResponseEntity<>(myEntry,HttpStatus.CREATED);

            }
            catch(Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }
        }

        @GetMapping("id/{myId}")
        public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId){
            Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        @DeleteMapping("id/{myId}")
        public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
             journalEntryService.deleteById(myId);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        @DeleteMapping()
        public ResponseEntity<JournalEntry> deleteAll(){
            journalEntryService.deleteAll();
            return  new ResponseEntity<>(HttpStatus.OK);
        }




        @PutMapping("/id/{id}")
            public ResponseEntity <JournalEntry> updateJournalById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
//                JournalEntry old=journalEntryService.findById(id).orElse(null);
//                if(old!=null){
//                    old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
//                    old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
//                    journalEntryService.saveEntry(old, user);
//                    return new ResponseEntity<>(old,HttpStatus.OK);
//                }
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }


