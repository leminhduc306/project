package com.library_management.library.service;

import com.library_management.library.constant.BorrowedStatus;
import com.library_management.library.entity.Book;
import com.library_management.library.entity.BorrowedBook;
import com.library_management.library.entity.User;

import java.util.List;

public interface IBorrowedBookService {
    String borrowBook(Integer userId, Integer bookId);

    int countByUserUserIdAndBorrowedStatus(Integer user_userId, BorrowedStatus borrowedStatus);

    BorrowedBook findByBorrowId(Integer borrowId);

    List<BorrowedBook> findByUserId(Integer userId);


}
