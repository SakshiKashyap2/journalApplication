package com.exampleJournalApplication.service;

import com.exampleJournalApplication.entity.JournalEntry;
import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public  class UserService{
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();



    public void saveUser(User user){
        userRepository.save(user);

    }
    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;

        } catch (Exception e) {
            log.error("Error Occured for {} :",user.getUserName(),e);
            log.warn("hahahaahahahah");
            log.info("hahahahahahahaha");
            return false;
        }


    }

    public void saveAdmin(User user) {
        user.setPassword(Objects.requireNonNull(passwordEncoder.encode(user.getPassword())));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id){
        return userRepository.findById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

     public void deleteById(ObjectId id){
        userRepository.deleteById(id);

     }



}
