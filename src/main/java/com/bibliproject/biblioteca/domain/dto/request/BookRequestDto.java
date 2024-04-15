package com.bibliproject.biblioteca.domain.dto.request;

import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
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
    private List<LoanResponseDto> loans;
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
