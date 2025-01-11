package com.library_management.library.service;


import com.library_management.library.dto.Email.MessageRequest;

public interface IEmailService {
    void sendEmail(MessageRequest messageRequest);
}
