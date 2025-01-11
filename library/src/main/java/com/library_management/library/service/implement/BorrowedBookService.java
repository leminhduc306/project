package com.library_management.library.service.implement;

import com.library_management.library.constant.BorrowedStatus;
import com.library_management.library.constant.ErrorCode;
import com.library_management.library.entity.Book;
import com.library_management.library.entity.BorrowedBook;
import com.library_management.library.entity.User;
import com.library_management.library.exception.ApiException;
import com.library_management.library.repository.BookRepository;
import com.library_management.library.repository.BorrowedBookRepository;
import com.library_management.library.repository.UserRepository;
import com.library_management.library.service.IBorrowedBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowedBookService implements IBorrowedBookService {

    BorrowedBookRepository borrowedBookRepository;
    BookRepository bookRepository;
    UserRepository userRepository;


    @Override
    public String borrowBook(Integer userId, Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_EXIST));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));

        if (book.getQuantity() <= 0) {
            throw new ApiException(ErrorCode.BOOK_OUT_STOCK);
        }

        if (borrowedBookRepository.countByUserUserIdAndBorrowedStatus(userId, BorrowedStatus.BORROWED) >= 5) {
            throw new ApiException(ErrorCode.MAX_BOOKS_BORROWED);
        }

        if (borrowedBookRepository.existsByUserUserIdAndBookBookIdAndBorrowedStatus(userId, bookId, BorrowedStatus.BORROWED)) {
            throw new ApiException(ErrorCode.DUPLICATE_BORROW);
        }

        // Decrease quantity
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // set Borrowed book
        BorrowedBook borrowedBook = BorrowedBook.builder()
                .user(user)
                .book(book)
                .borrowDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusWeeks(2))
                .borrowedStatus(BorrowedStatus.BORROWED)
                .build();
        borrowedBookRepository.save(borrowedBook);
        return "Book" + book.getTitle() + "borrowed successfully!";
    }

    @Override
    public int countByUserUserIdAndBorrowedStatus(Integer user_userId, BorrowedStatus borrowedStatus) {
        return borrowedBookRepository.countByUserUserIdAndBorrowedStatus(user_userId, borrowedStatus);
    }

    @Override
    public BorrowedBook findByBorrowId(Integer borrowId) {
        return borrowedBookRepository.findByBorrowId(borrowId);
    }

    @Override
    public List<BorrowedBook> findByUserId(Integer userId) {
        return borrowedBookRepository.findByUserUserId(userId);
    }


}

