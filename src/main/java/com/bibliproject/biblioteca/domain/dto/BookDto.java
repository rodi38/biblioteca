package com.bibliproject.biblioteca.domain.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookDto {

    private int stockQuantity;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private String publisher;
}
