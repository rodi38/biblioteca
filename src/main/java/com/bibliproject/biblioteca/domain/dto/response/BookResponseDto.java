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
    private String author;
    private List<LoanResponseDto> loan;
    private boolean hasOnStock;
    private String category;
    private String isbn;
    private String publisher;
    private int publishedYear;

    public void isStockZero() {
        if (stockQuantity == 0) {
            this.hasOnStock = false;
        }
        this.hasOnStock = true;
    }
}
