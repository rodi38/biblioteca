package com.bibliproject.biblioteca.domain.dto;

import com.bibliproject.biblioteca.domain.dto.request.AuthorDto;
import com.bibliproject.biblioteca.domain.dto.request.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {

    private int stockQuantity;
    private String title;
    private AuthorDto author;
    private CategoryDto categories;
    private String isbn;
    private String publisher;
    private int publishedYear;
}
