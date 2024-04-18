package com.bibliproject.biblioteca.domain.dto.simple.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleBookResponse {
    private Long id;
    private String title;
    private String author;
    private String category;
}
