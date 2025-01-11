package com.library_management.library.entity;

import com.library_management.library.constant.BorrowedStatus;
import com.library_management.library.repository.BorrowedBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor

public class BorrowStatusUpdate {

    BorrowedBookRepository borrowedBookRepository;

    @Scheduled(cron = "* * * * * ?")
    public void updateOverdueBooks() {
        LocalDateTime now = LocalDateTime.now();
        List<BorrowedBook> overdueBooks = borrowedBookRepository.findBorrowedBooksByBorrowedStatusAndDueDateBefore(
                BorrowedStatus.BORROWED, now);

        for (BorrowedBook borrowedBook : overdueBooks) {
            borrowedBook.setBorrowedStatus(BorrowedStatus.OVERDUE);
            borrowedBookRepository.save(borrowedBook);
        }

        System.out.println("Updated overdue books: " + overdueBooks.size());
    }
}
