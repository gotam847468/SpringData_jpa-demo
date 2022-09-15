package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
