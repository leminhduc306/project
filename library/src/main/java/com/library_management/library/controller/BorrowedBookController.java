package com.library_management.library.controller;

import com.library_management.library.constant.BorrowedStatus;
import com.library_management.library.dto.Email.MessageRequest;
import com.library_management.library.dto.Page.JsoneResponse;
import com.library_management.library.entity.Book;
import com.library_management.library.entity.BorrowedBook;
import com.library_management.library.entity.User;
import com.library_management.library.service.IBookService;
import com.library_management.library.service.IBorrowedBookService;
import com.library_management.library.service.IEmailService;
import com.library_management.library.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
@AllArgsConstructor
public class BorrowedBookController {

    IBorrowedBookService borrowedBookService;
    IEmailService emailService;
    IUserService userService;
    IBookService bookService;

    @PostMapping
    public ResponseEntity<?> borrowBook(@RequestParam Integer userId, @RequestParam Integer bookId) {
        String result = borrowedBookService.borrowBook(userId, bookId);
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (result.equals("Book borrowed successfully!")) {
            MessageRequest messageRequest = new MessageRequest();
            messageRequest.setFrom("Library <leminhducqvbn@gmail.com>");
            messageRequest.setTo(user.getEmail());
            messageRequest.setToName(user.getFirstName() + " " + user.getLastName());
            messageRequest.setSubject("Book borrowed successfully!");
            messageRequest.setContent("You borrowed successfully the book with title: " + book.getTitle());
            emailService.sendEmail(messageRequest);
            return JsoneResponse.ok(result);
        }
        return JsoneResponse.notFound(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBorrowedBook(@PathVariable Integer userId) {
        List<BorrowedBook> borrowedBookList = borrowedBookService.findByUserId(userId);
        if (borrowedBookList.isEmpty()) {
            return JsoneResponse.notFound("Borrowed book not found!");
        }
        return JsoneResponse.ok(borrowedBookList);
    }


}
