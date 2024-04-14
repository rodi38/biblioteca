package com.bibliproject.biblioteca.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDto {
    private int stockQuantity;
    private String title;
    private AuthorDto author;
    private CategoryDto category;
    private String isbn;
    private String publisher;
    private int publishedYear;
}
