package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book toEntity(BookResponseDto bookResponseDto) {
        Book book = new Book();
        book.setId(bookResponseDto.getId());
        book.setStockQuantity(bookResponseDto.getStockQuantity());
        book.setTitle(bookResponseDto.getTitle());
        book.setAuthor(bookResponseDto.getAuthor());
        book.setPublisher(bookResponseDto.getPublisher());
        book.setCategory(bookResponseDto.getCategory());
        book.setIsbn(bookResponseDto.getIsbn());
        book.setPublishedYear(bookResponseDto.getPublishedYear());
        return book;
    }

    public static List<Book> toEntityList(List<BookResponseDto> bookResponseDto) {
        if (bookResponseDto == null) {
            return null;
        }
        List<Book> books = new ArrayList<>();

        for (BookResponseDto bookResponseDto1 : bookResponseDto) {
            books.add(toEntity(bookResponseDto1));
        }

        return books;
    }

    public static BookResponseDto toDtoResponse(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setStockQuantity(book.getStockQuantity());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setPublisher(book.getPublisher());
        bookResponseDto.setCategory(book.getCategory());
        bookResponseDto.setIsbn(book.getIsbn());
        bookResponseDto.setPublishedYear(book.getPublishedYear());
        return bookResponseDto;
    }

    public static List<BookResponseDto> toDtoList(List<Book> books) {
        if (books == null) {
            return null;
        }
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for (Book bookResponseDto1 : books) {
            bookResponseDtos.add(toDtoResponse(bookResponseDto1));
        }

        return bookResponseDtos;

    }

    public static Book dtoRequestToEntity(BookRequestDto bookRequestDto) {
        Book book = new Book();

        book.setStockQuantity(bookRequestDto.getStockQuantity());
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setPublisher(bookRequestDto.getPublisher());
        book.setCategory(bookRequestDto.getCategory());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setPublishedYear(bookRequestDto.getPublishedYear());

        return book;
    }

    public static void bookUpdate(Book book, BookRequestDto bookRequestDto) {

        book.setStockQuantity(bookRequestDto.getStockQuantity());
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setPublisher(bookRequestDto.getPublisher());
        book.setCategory(bookRequestDto.getCategory());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setPublishedYear(bookRequestDto.getPublishedYear());
        book.setIsbn(bookRequestDto.getIsbn());

    }
//
}