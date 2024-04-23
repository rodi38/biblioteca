package com.bibliproject.biblioteca.domain.dto.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookRequestDto {
    private int stockQuantity;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private String publisher;
    private int publishedYear;

}