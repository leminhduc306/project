package com.library_management.library.repository;

import com.library_management.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = """ 
            SELECT b FROM Book b WHERE  
            (:title IS NULL OR b.title LIKE CONCAT('%', :title, '%')) AND   
            (:author IS NULL OR b.author LIKE CONCAT('%', :author, '%')) AND  
            (:publishYear IS NULL OR b.publishYear = :publishYear) AND
            (:minPrice IS NULL OR :maxPrice IS NULL OR b.price BETWEEN :minPrice AND :maxPrice) AND
            (:categoryName IS NULL OR b.category.name = :categoryName)
            """)
    List<Book> findByAttributes(
            @Param("title") String title,
            @Param("author") String author,
            @Param("publishYear") Integer publishYear,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("categoryName") String categoryName
    );
}
