package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookUseCaseTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    void returnsTheBookWhenFound() {
        Book book = new Book();
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(book));

        Book result = new FindBookUseCase(bookRepository).execute(1L);

        assertThat(result).isSameAs(book);
    }

    @Test
    void throwsWhenNotFound() {
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new FindBookUseCase(bookRepository).execute(1L))
                .isInstanceOf(BookNotFoundException.class);
    }
}
