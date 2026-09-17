package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateBookUseCaseTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    void savesAndReturnsTheBook() {
        Book book = new Book();
        when(bookRepository.save(book)).thenReturn(book);

        Book result = new CreateBookUseCase(bookRepository).execute(book);

        assertThat(result).isSameAs(book);
        verify(bookRepository).save(book);
    }
}
