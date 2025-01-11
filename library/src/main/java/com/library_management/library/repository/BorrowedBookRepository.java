package com.library_management.library.repository;

import com.library_management.library.constant.BorrowedStatus;
import com.library_management.library.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    int countByUserUserIdAndBorrowedStatus(Integer user_userId, BorrowedStatus borrowedStatus);
    BorrowedBook findByBorrowId(Integer borrowId);
    boolean existsByUserUserIdAndBookBookIdAndBorrowedStatus(Integer userId, Integer bookId, BorrowedStatus borrowedStatus);
    List<BorrowedBook> findByUserUserId(Integer user_id);
    List<BorrowedBook> findBorrowedBooksByBorrowedStatusAndDueDateBefore(BorrowedStatus borrowedStatus, LocalDateTime now);
}
