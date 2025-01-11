package com.library_management.library.entity;

import com.library_management.library.constant.ReturnStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "return_books")
public class ReturnBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer returnId;

    LocalDateTime returnDate;
    Double fine;

    @Enumerated(EnumType.STRING)
    ReturnStatus returnStatus;

    @ManyToOne
    @JoinColumn(name = "borrow_id", nullable = false)
    BorrowedBook borrowedBook;


}
