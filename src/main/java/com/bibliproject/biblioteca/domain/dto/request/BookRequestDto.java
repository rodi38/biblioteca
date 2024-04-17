package com.bibliproject.biblioteca.domain.dto.request;

import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.entity.Loan;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookRequestDto {
    private int stockQuantity;
    private String title;
    private String author;
    //private List<Loan> loans;
    private String category;
    private String isbn;
    private String publisher;
    private int publishedYear;

}