package com.library_management.library.entity;

import com.library_management.library.constant.BorrowedStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Table(name = "borrowed_books")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer borrowId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    LocalDateTime borrowDate;
    LocalDateTime dueDate;
    @Enumerated(EnumType.STRING)
    BorrowedStatus borrowedStatus;
}
