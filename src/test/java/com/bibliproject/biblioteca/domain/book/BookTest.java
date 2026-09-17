package com.bibliproject.biblioteca.domain.book;

import com.bibliproject.biblioteca.exception.book.BookOutOfStockException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

    @Test
    void isAvailableWhenStockIsPositive() {
        Book book = new Book();
        book.setStockQuantity(1);

        assertThat(book.isAvailable()).isTrue();
    }

    @Test
    void isNotAvailableWhenStockIsZero() {
        Book book = new Book();
        book.setStockQuantity(0);

        assertThat(book.isAvailable()).isFalse();
    }

    @Test
    void decreaseStockReducesQuantityAndTouchesUpdatedAt() {
        Book book = new Book();
        book.setStockQuantity(2);

        book.decreaseStock();

        assertThat(book.getStockQuantity()).isEqualTo(1);
        assertThat(book.getUpdatedAt()).isNotNull();
    }

    @Test
    void decreaseStockThrowsWhenOutOfStock() {
        Book book = new Book();
        book.setStockQuantity(0);

        assertThatThrownBy(book::decreaseStock)
                .isInstanceOf(BookOutOfStockException.class)
                .hasMessage("Livro fora de estoque.");
    }

    @Test
    void increaseStockRaisesQuantityAndTouchesUpdatedAt() {
        Book book = new Book();
        book.setStockQuantity(3);

        book.increaseStock();

        assertThat(book.getStockQuantity()).isEqualTo(4);
        assertThat(book.getUpdatedAt()).isNotNull();
    }

    @Test
    void markDeletedFlagsBookAndSetsDeletedAt() {
        Book book = new Book();

        book.markDeleted();

        assertThat(book.isDeleted()).isTrue();
        assertThat(book.getDeletedAt()).isNotNull();
    }
}
