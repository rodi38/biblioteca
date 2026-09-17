package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.book.Isbn;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateBookUseCaseTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    void updatesAllFieldsAndSaves() {
        Book book = new Book();
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book result = new UpdateBookUseCase(bookRepository).execute(1L, "Title", "Author", "Category",
                new Isbn("123"), "Publisher", 2020, 5);

        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getAuthor()).isEqualTo("Author");
        assertThat(result.getCategory()).isEqualTo("Category");
        assertThat(result.getIsbn().value()).isEqualTo("123");
        assertThat(result.getPublisher()).isEqualTo("Publisher");
        assertThat(result.getPublishedYear()).isEqualTo(2020);
        assertThat(result.getStockQuantity()).isEqualTo(5);
        assertThat(result.getUpdatedAt()).isNotNull();
    }

    @Test
    void throwsWhenBookNotFound() {
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new UpdateBookUseCase(bookRepository)
                .execute(1L, "T", "A", "C", new Isbn("123"), "P", 2020, 1))
                .isInstanceOf(BookNotFoundException.class);
    }
}
