package com.library_management.library.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @Column(name = "book_id")
    Integer bookId;
    String title;
    String description;
    Integer publishYear;
    String author;
    Integer quantity;
    Double price;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}

