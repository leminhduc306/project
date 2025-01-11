package com.library_management.library.repository;

import com.library_management.library.entity.ReturnBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnBookRepository extends JpaRepository<ReturnBook, Integer> {

}
