package com.bibliproject.biblioteca.domain.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {

    private int stockQuantity;
    private String title;
    private AuthorDto author;
    private List<CategoryDto> categories;
    private String isbn;
    private String publisher;
    private int publishedYear;
}
