package com.bibliproject.biblioteca.domain.book;

import com.bibliproject.biblioteca.exception.book.BookOutOfStockException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    private Long id;
    private int stockQuantity;
    private String title;
    private String author;
    private String category;
    private Isbn isbn;
    private String publisher;
    private int publishedYear;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean deleted;

    public boolean isAvailable() {
        return stockQuantity > 0;
    }

    public void decreaseStock() {
        if (!isAvailable()) {
            throw new BookOutOfStockException("Livro fora de estoque.");
        }
        stockQuantity--;
        updatedAt = LocalDateTime.now();
    }

    public void increaseStock() {
        stockQuantity++;
        updatedAt = LocalDateTime.now();
    }

    public void markDeleted() {
        deleted = true;
        deletedAt = LocalDateTime.now();
    }
}
