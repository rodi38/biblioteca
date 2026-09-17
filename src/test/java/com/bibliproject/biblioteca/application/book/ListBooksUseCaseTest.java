package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListBooksUseCaseTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    void delegatesToSearchQueryWhenSearchIsGiven() {
        Pageable pageable = Pageable.unpaged();
        Page<Book> page = new PageImpl<>(java.util.List.of());
        when(bookRepository.findAllNotDeletedAndMatchesSearch("term", pageable)).thenReturn(page);

        Page<Book> result = new ListBooksUseCase(bookRepository).execute("term", pageable);

        assertThat(result).isSameAs(page);
        verify(bookRepository).findAllNotDeletedAndMatchesSearch("term", pageable);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void delegatesToListAllWhenSearchIsNull() {
        Pageable pageable = Pageable.unpaged();
        Page<Book> page = new PageImpl<>(java.util.List.of());
        when(bookRepository.findAllNotDeleted(pageable)).thenReturn(page);

        Page<Book> result = new ListBooksUseCase(bookRepository).execute(null, pageable);

        assertThat(result).isSameAs(page);
        verify(bookRepository).findAllNotDeleted(pageable);
        verifyNoMoreInteractions(bookRepository);
    }
}
