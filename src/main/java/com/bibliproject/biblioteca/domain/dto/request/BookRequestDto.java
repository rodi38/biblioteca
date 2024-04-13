package com.bibliproject.biblioteca.domain.dto.request;

import com.bibliproject.biblioteca.domain.dto.AuthorDto;
import com.bibliproject.biblioteca.domain.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDto {

    private int stockQuantity;
    private String title;
    private AuthorDto author;
    private List<CategoryDto> categories;
    private String isbn;
    private String publisher;
    private int publishedYear;
}
