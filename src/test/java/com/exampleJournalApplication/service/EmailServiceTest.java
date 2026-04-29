package com.exampleJournalApplication.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    void testsendMail(){
        emailService.sendEmail("0909sakshikumari@gmail.com","Testing java mail Sender",
                "Hi,Aap kaise hain ?");
    }
}
