package com.exampleJournalApplication.service;

import com.exampleJournalApplication.entity.JournalEntry;
import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public  class UserService{
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
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
