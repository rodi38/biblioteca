package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Category;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.repository.AuthorRepository;
import com.bibliproject.biblioteca.repository.BookRepository;
import com.bibliproject.biblioteca.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;


    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return BookMapper.INSTANCE.convertEntityListToListDto(books);
    }

    public BookDto createBook(BookDto bookDTO) {
        Book book = BookMapper.INSTANCE.convertDtoToEntity(bookDTO);
        System.out.println("created");

//        authorRepository.saveAndFlush(book.getAuthor());
//        categoryRepository.saveAllAndFlush(book.getCategories());
        if (book.getAuthor() != null) {
            book.getAuthor().setBook(book);
        }

        if (book.getCategories() != null) {
            for (Category category : book.getCategories()) {
                category.setBook(book);
            }
        }
        bookRepository.saveAndFlush(book);
        return BookMapper.INSTANCE.convertEntityToDto(book);
    }

    public BookDto updateBook(BookDto bookDTO) {
        return null;
    }

    public  BookDto deleteBook(BookDto bookDTO) {
        return null;
    }
}
