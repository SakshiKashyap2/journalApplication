package com.exampleJournalApplication.repository;

import com.exampleJournalApplication.entity.User;
import com.exampleJournalApplication.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepository.getUserForSA());

    }
}
