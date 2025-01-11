package com.library_management.library.controller;

import com.library_management.library.constant.ErrorCode;
import com.library_management.library.dto.Page.JsoneResponse;
import com.library_management.library.entity.Book;
import com.library_management.library.exception.ApiException;
import com.library_management.library.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    IBookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findByAttribute(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publishYear,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String categoryName
    ) {
        List<Book> books = bookService.findByAttribute(title, author, publishYear, minPrice, maxPrice, categoryName);
        if (books == null || books.isEmpty()) {
            throw new ApiException(ErrorCode.BOOK_NOT_EXIST);
        }
        return ResponseEntity.ok(books);
    }
}


