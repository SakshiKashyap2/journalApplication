package com.exampleJournalApplication.service;

import com.exampleJournalApplication.entity.JournalEntry;
import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.repository.JournalEntryRepository;
import com.exampleJournalApplication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private UserService userService;
    @Autowired
    private JournalEntryRepository journalEntryRepository;


     public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user=userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);

        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry.",e);

        }
     }


    public void saveEntry(JournalEntry journalEntry){
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);

    }

     public List<JournalEntry> getAll(){
         return journalEntryRepository.findAll();
     }

     public Optional<JournalEntry> findById(ObjectId myId){
         return journalEntryRepository.findById(myId);

     }

    public void deleteById(ObjectId myId , String userName){
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        userService.saveUser(user);
        journalEntryRepository.deleteById(myId);
    }

    public void deleteAll(){
        journalEntryRepository.deleteAll();
    }



}
