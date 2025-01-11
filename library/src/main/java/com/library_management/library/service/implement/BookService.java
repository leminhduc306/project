package com.library_management.library.service.implement;

import com.library_management.library.constant.ErrorCode;
import com.library_management.library.entity.Book;
import com.library_management.library.exception.ApiException;
import com.library_management.library.repository.BookRepository;
import com.library_management.library.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    BookRepository bookRepository;

    @Override
    public List<Book> findByAttribute(String title, String author, Integer publishYear, Double minPrice, Double maxPrice, String categoryName) {
        return bookRepository.findByAttributes(title, author, publishYear, minPrice, maxPrice, categoryName);
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_EXIST));
    }
}