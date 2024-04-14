package com.bibliproject.biblioteca.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDto {
    private long id;
    private int stockQuantity;
    private String title;
    private AuthorResponseDto author;
    private CategoryResponseDto category;
    private String isbn;
    private String publisher;
    private int publishedYear;
}
