package com.bibliproject.biblioteca.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stockQuantity;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private String publisher;


}
