package com.library_management.library.controller;

import com.library_management.library.constant.ReturnStatus;
import com.library_management.library.dto.Email.MessageRequest;
import com.library_management.library.dto.Page.JsoneResponse;
import com.library_management.library.entity.BorrowedBook;
import com.library_management.library.service.implement.BorrowedBookService;
import com.library_management.library.service.implement.EmailService;
import com.library_management.library.service.implement.ReturnBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
@AllArgsConstructor
public class ReturnBookController {
    ReturnBookService returnBookService;
    BorrowedBookService borrowedBookService;
    EmailService emailService;

    @PostMapping("/{borrowId}")
    public ResponseEntity<?> returnBook(
            @PathVariable Integer borrowId,
            @RequestParam ReturnStatus returnStatus) {
        String response = returnBookService.returnBook(borrowId, returnStatus);
        BorrowedBook borrowedBook = borrowedBookService.findByBorrowId(borrowId);

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setFrom("Library <leminhducqvbn@gmail.com>");
        messageRequest.setTo(borrowedBook.getUser().getEmail());
        messageRequest.setToName(borrowedBook.getUser().getFirstName() + " " + borrowedBook.getUser().getLastName());
        messageRequest.setSubject("Return book successfully!");
        messageRequest.setContent(response);
        emailService.sendEmail(messageRequest);
        return JsoneResponse.ok(response);
    }
}
