package com.exampleJournalApplication.service;

import com.exampleJournalApplication.entity.JournalEntry;
import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.repository.JournalEntryRepository;
import com.exampleJournalApplication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
    @Transactional
    public boolean deleteById(ObjectId myId , String userName){
         boolean removed =false;
         try{
             User user=userService.findByUserName(userName);
             removed=user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
             if(removed){
                 userService.saveUser(user);
                 journalEntryRepository.deleteById(myId);

             }
         } catch (Exception e) {
             log.error("Error",e);
             throw new RuntimeException("An error occurred while saving the entry.",e);
         }return  removed;


    }

    public void deleteAll(){
        journalEntryRepository.deleteAll();
    }



}
