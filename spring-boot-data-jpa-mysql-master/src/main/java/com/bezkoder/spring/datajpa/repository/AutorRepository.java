package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Author, Long> {

}
