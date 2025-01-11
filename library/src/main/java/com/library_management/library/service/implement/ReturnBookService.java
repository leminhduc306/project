package com.library_management.library.service.implement;

import com.library_management.library.constant.BorrowedStatus;
import com.library_management.library.constant.ErrorCode;
import com.library_management.library.constant.ReturnStatus;
import com.library_management.library.entity.Book;
import com.library_management.library.entity.BorrowedBook;
import com.library_management.library.entity.ReturnBook;
import com.library_management.library.exception.ApiException;
import com.library_management.library.repository.BookRepository;
import com.library_management.library.repository.BorrowedBookRepository;
import com.library_management.library.repository.ReturnBookRepository;
import com.library_management.library.service.IReturnBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Service
public class ReturnBookService implements IReturnBookService {

    BorrowedBookRepository borrowedBookRepository;
    BookRepository bookRepository;
    ReturnBookRepository returnBookRepository;

    @Override
    @Transactional
    public String returnBook(Integer borrowId, ReturnStatus returnStatus) {

        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowId)
                .orElseThrow(() -> new ApiException(ErrorCode.BORROW_NOT_EXIST));

        if (borrowedBook.getBorrowedStatus().equals(BorrowedStatus.RETURNED)) {
            throw new ApiException(ErrorCode.BOOK_IS_RETURNED);
        }

        Book book = borrowedBook.getBook();
        double fine = 0;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueDate = borrowedBook.getDueDate();


        if (returnStatus.equals(ReturnStatus.DAMAGED)) {
            if (ChronoUnit.DAYS.between(dueDate, now) >= 0) {
                fine += ChronoUnit.DAYS.between(dueDate, now) * 5;
            }
            fine += (book.getPrice() * 0.5);
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
        } else if (returnStatus.equals(ReturnStatus.LOST)) {
            fine += book.getPrice();
        } else if (returnStatus.equals(ReturnStatus.NORMAL)) {
            if (ChronoUnit.DAYS.between(dueDate, now) >= 0) {
                fine += ChronoUnit.DAYS.between(dueDate, now) * 5;
            }
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
        }

        ReturnBook returnBook = ReturnBook.builder()
                .borrowedBook(borrowedBook)
                .fine(fine)
                .returnDate(LocalDateTime.now())
                .returnStatus(returnStatus)
                .build();
        returnBookRepository.save(returnBook);

        borrowedBook.setBorrowedStatus(BorrowedStatus.RETURNED);
        borrowedBookRepository.save(borrowedBook);

        return fine > 0 ? "Book" + book.getTitle() + " returned successfully! Fine: $" + fine : "Book returned successfully!";
    }
}
