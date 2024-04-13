package com.bibliproject.biblioteca.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book")
    private Book book;
}
