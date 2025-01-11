package com.library_management.library.controller;

import com.library_management.library.dto.Email.MessageRequest;
import com.library_management.library.service.implement.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody MessageRequest messageRequest) {
        emailService.sendEmail(messageRequest);
    }
}
