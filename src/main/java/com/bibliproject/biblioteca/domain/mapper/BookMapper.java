package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book toEntityWithoutLoans(BookResponseDto bookResponseDto) {
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
    public static List < Book > toEntityListWithoutLoans(List < BookResponseDto > bookResponseDto) {
        if (bookResponseDto == null) {
            return null;
        }
        List < Book > books = new ArrayList < > ();

        for (BookResponseDto bookResponseDto1: bookResponseDto) {
            books.add(toEntityWithoutLoans(bookResponseDto1));
        }

        return books;
    }

    public static BookResponseDto toDtoWithoutLoans(Book book) {
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

    public static List < BookResponseDto > toDtoListWithoutLoans(List < Book > books) {
        if (books == null) {
            return null;
        }
        List < BookResponseDto > bookResponseDtos = new ArrayList < > ();
        for (Book bookResponseDto1: books) {
            bookResponseDtos.add(toDtoWithoutLoans(bookResponseDto1));
        }

        return bookResponseDtos;

    }

    //with loans
//
//    public static Book toEntity(BookResponseDto bookResponseDto) {
//        if (bookResponseDto == null) {
//            return null;
//        }
//
//        Book book = new Book();
//
//        book.setId(bookResponseDto.getId());
//        book.setStockQuantity(bookResponseDto.getStockQuantity());
//        book.setTitle(bookResponseDto.getTitle());
//        book.setAuthor(bookResponseDto.getAuthor());
//        book.setPublisher(bookResponseDto.getPublisher());
//        book.setCategory(bookResponseDto.getCategory());
//        book.setPublishedYear(bookResponseDto.getPublishedYear());
//        book.setIsbn(bookResponseDto.getIsbn());
//        book.setLoans(LoanMapper.toEntityList(bookResponseDto.getLoans()));
//
//        return book;
//    }
//
//    public static Book dtoRequestToEntity(BookRequestDto bookRequestDto) {
//        Book book = new Book();
//
//        book.setStockQuantity(bookRequestDto.getStockQuantity());
//        book.setTitle(bookRequestDto.getTitle());
//        book.setAuthor(bookRequestDto.getAuthor());
//        book.setPublisher(bookRequestDto.getPublisher());
//        book.setCategory(bookRequestDto.getCategory());
//        book.setIsbn(bookRequestDto.getIsbn());
//        book.setPublishedYear(bookRequestDto.getPublishedYear());
//        book.setLoans(null);
//
//        return book;
//    }
//
//    public static BookRequestDto entityToDtoRequest(Book book) {
//        BookRequestDto bookRequestDto = new BookRequestDto();
//
//        bookRequestDto.setStockQuantity(book.getStockQuantity());
//        bookRequestDto.setTitle(book.getTitle());
//        bookRequestDto.setAuthor(book.getAuthor());
//        bookRequestDto.setPublisher(book.getPublisher());
//        bookRequestDto.setCategory(book.getCategory());
//        bookRequestDto.setIsbn(book.getIsbn());
//        bookRequestDto.setPublishedYear(book.getPublishedYear());
//        //bookRequestDto.setLoans(LoanMapper.toDtoListWithoutLoans(book.getLoans()));
//
//        return bookRequestDto;
//    }
//
//    public static BookResponseDto toDto(Book book) {
//        BookResponseDto bookResponseDto = new BookResponseDto();
//
//        bookResponseDto.setId(book.getId());
//        bookResponseDto.setStockQuantity(book.getStockQuantity());
//        bookResponseDto.setTitle(book.getTitle());
//        bookResponseDto.setAuthor(book.getAuthor());
//        bookResponseDto.setPublisher(book.getPublisher());
//        bookResponseDto.setCategory(book.getCategory());
//        bookResponseDto.setIsbn(book.getIsbn());
//        bookResponseDto.setPublishedYear(book.getPublishedYear());
//        bookResponseDto.setLoans(LoanMapper.toDtoList(book.getLoans()));
//
//        return bookResponseDto;
//    }
//
//    public static List < BookResponseDto > toDtoList(List < Book > books) {
//        if (books == null) {
//            return null;
//        }
//        List < BookResponseDto > bookResponseDtos = new ArrayList < > ();
//        for (Book bookResponseDto1: books) {
//            bookResponseDtos.add(toDto(bookResponseDto1));
//        }
//
//        return bookResponseDtos;
//
//    }
//    //    public static List<Book>  toEntityList(List<BookResponseDto> bookResponseDto) {
//    //        if (bookResponseDto == null) {
//    //            return null;
//    //        }
//    //        List<Book> books = new ArrayList<>();
//    //
//    //        for (BookResponseDto bookResponseDto1 : bookResponseDto) {
//    //            books.add(toEntityWithoutLoans(bookResponseDto1));
//    //        }
//    //
//    //        return  books;
//    //    }
//
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