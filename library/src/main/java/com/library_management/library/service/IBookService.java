package com.library_management.library.service;

import com.library_management.library.entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface IBookService {
    List<Book> findByAttribute(String title, String author, Integer publishYear, Double minPrice, Double maxPrice, String categoryName);
    Book getBookById(Integer bookId);

}
