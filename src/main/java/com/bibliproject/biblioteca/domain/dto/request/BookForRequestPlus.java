package com.bibliproject.biblioteca.domain.dto.request;


import com.bibliproject.biblioteca.domain.dto.response.AuthorResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.CategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookForRequestPlus {
    private int stockQuantity;
    private String title;
    private AuthorResponseDto author;
    private CategoryResponseDto category;
    private String isbn;
    private String publisher;
    private int publishedYear;
}
