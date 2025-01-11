package com.library_management.library.service;

import com.library_management.library.constant.ReturnStatus;

public interface IReturnBookService {
    String returnBook(Integer borrowId, ReturnStatus returnStatus);
}
