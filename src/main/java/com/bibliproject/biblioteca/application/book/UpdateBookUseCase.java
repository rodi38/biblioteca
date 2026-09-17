package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.book.Isbn;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateBookUseCase {

    private final BookRepository bookRepository;

    public UpdateBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book execute(Long id, String title, String author, String category, Isbn isbn,
                         String publisher, int publishedYear, int stockQuantity) {
        Book book = bookRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setPublishedYear(publishedYear);
        book.setStockQuantity(stockQuantity);
        book.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }
}
