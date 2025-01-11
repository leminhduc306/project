package com.library_management.library.constant;

import com.library_management.library.entity.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {

    Category_NOT_EXIST(404, "Category is not exist", HttpStatus.NOT_FOUND),
    USER_NOT_EXIST(404, "User is not exist", HttpStatus.NOT_FOUND),
    BOOK_NOT_EXIST(404, "Book is not exist", HttpStatus.NOT_FOUND),
    BORROW_NOT_EXIST(404, "Borrow is not exist", HttpStatus.NOT_FOUND),
    BOOK_IS_RETURNED(400, "Book has been returned", HttpStatus.BAD_REQUEST),
    BOOK_OUT_STOCK(409, "Sorry, the book is out of stock!", HttpStatus.CONFLICT),
    MAX_BOOKS_BORROWED(400, "You can borrow max 5 books", HttpStatus.BAD_REQUEST),
    DUPLICATE_BORROW(400, "You cannot borrow the same book twice.", HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus status;
}
