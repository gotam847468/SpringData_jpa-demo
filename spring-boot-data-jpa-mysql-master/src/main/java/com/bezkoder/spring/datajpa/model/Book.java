package com.bezkoder.spring.datajpa.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String publisher;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public Book(String publisher, String title, Author author) {
        this.publisher = publisher;
        this.title = title;
        this.author = author;
    }
}
