package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Author;
import com.bezkoder.spring.datajpa.model.Book;
import com.bezkoder.spring.datajpa.repository.AutorRepository;
import com.bezkoder.spring.datajpa.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthoController {
    private final AutorRepository autorRepository;
    private final BookRepository bookRepository;

    public AuthoController(AutorRepository autorRepository, BookRepository bookRepository) {
        this.autorRepository = autorRepository;
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    Iterable<Author> authors() {
        return autorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id) {
        return autorRepository.findById(id);
    }

    @MutationMapping
    Book addBook(@Argument BookInput book) {
        Author author = autorRepository.findById(book.authorId()).orElseThrow(() -> new IllegalArgumentException("not found"));
        Book b = new Book(book.publisher(), book.title(), author);
        return bookRepository.save(b);
    }

    @MutationMapping
    Status deleteBook(@Argument Long id) {
        try {
            bookRepository.deleteById(id);
            return new Status("200", "Success");
        } catch (Exception e) {
            return new Status("200", "not able to delete");
        }
    }

    record BookInput(String title, String publisher, Long authorId) {
    }

    record Status(String statusCode, String message) {
    }
}

