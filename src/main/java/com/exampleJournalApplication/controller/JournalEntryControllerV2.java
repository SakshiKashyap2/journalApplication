package com.exampleJournalApplication.controller;

import com.exampleJournalApplication.entity.JournalEntry;
import com.exampleJournalApplication.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

    @RestController
    @RequestMapping("/journal")
    public class JournalEntryControllerV2 {

        @Autowired
        private JournalEntryService journalEntryService;

        @GetMapping
        public List<JournalEntry> getAll(){
            return journalEntryService.getAll();

        }

        @PostMapping
        public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return myEntry;
        }

        @GetMapping("id/{myId}")
        public JournalEntry getJournalEntryById(@PathVariable Long myId){
            return null;

        }

        @DeleteMapping("id/{myId}")
        public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
            return null;

        }


        @PutMapping("/id/{id}")
        public JournalEntry updateJournalById(@PathVariable Long id,@RequestBody JournalEntry myEntry){
            return null;
        }

    }


