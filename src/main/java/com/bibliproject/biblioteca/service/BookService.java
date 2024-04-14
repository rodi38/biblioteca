package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.AuthorDto;
import com.bibliproject.biblioteca.domain.dto.request.BookForRequestPlus;
import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.AuthorResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.CategoryResponseDto;
import com.bibliproject.biblioteca.domain.entity.Author;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Category;
import com.bibliproject.biblioteca.domain.mapper.AuthorMapper;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.domain.mapper.CategoryMapper;
import com.bibliproject.biblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private final BookRepository bookRepository;

    private CategoryService categoryService;
    private AuthorService authorService;
    public BookService(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    public List<BookResponseDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return BookMapper.INSTANCE.convertEntityListToListDto(books);
    }


    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = BookMapper.INSTANCE.convertDtoRequestToEntity(bookRequestDto);
        System.out.println("created");

//        authorRepository.saveAndFlush(book.getAuthor());
//        categoryRepository.saveAllAndFlush(book.getCategories());
        if (book.getAuthor() != null) {
            book.getAuthor().setBook(book);
        }

        if (book.getCategory() != null) {
            book.getCategory().setBook(book);
        }
        bookRepository.saveAndFlush(book);
        return BookMapper.INSTANCE.convertEntityToDto(book);
    }


    public BookResponseDto updateBook(Long id, BookForRequestPlus bookRequestDto) {
        Book book = BookMapper.INSTANCE.convertDtoResponsetToEntity(findById(id));

        Author author = authorService.findById(bookRequestDto.getAuthor().getId()).get();
        BookMapper.INSTANCE.update(book, bookRequestDto);
        Category category = categoryService.findById(bookRequestDto.getCategory().getId()).get();

        // Vericando se os dados já existem no banco

        if (category != null) {
            Optional<Category> existingCategory = categoryService.findById(category.getId());
            if (existingCategory.isPresent()) {
                book.setCategory(existingCategory.get());
            }
            else {
                categoryService.create(category);
                book.setCategory(category);
            }
        }
        if (author != null) {
            Optional<Author> existingAuthor = authorService.findById(author.getId());
            if (existingAuthor.isPresent()) {
                book.setAuthor(existingAuthor.get());
            } else {
                authorService.create(author);
                book.setAuthor(author);
            }
        }

        Book savedBook = bookRepository.save(book);

        return BookMapper.INSTANCE.convertEntityToDto(savedBook);
    }
    public BookResponseDto findById(Long id) {
        return BookMapper.INSTANCE.convertEntityToDto(bookRepository.findById(id).get());
    }

    public  boolean deleteBook(long id) {

        return true;
    }
}
