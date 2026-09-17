package com.bibliproject.biblioteca.interfaces.mapper;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.Isbn;
import com.bibliproject.biblioteca.interfaces.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.interfaces.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.interfaces.dto.response.audity.BookAudityResponseDto;
import com.bibliproject.biblioteca.interfaces.dto.simple.response.book.SimpleBookResponse;

public final class BookDtoMapper {

    private BookDtoMapper() {
    }

    public static Book toDomain(BookRequestDto dto) {
        Book book = new Book();
        book.setStockQuantity(dto.getStockQuantity());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setIsbn(new Isbn(dto.getIsbn()));
        book.setPublisher(dto.getPublisher());
        book.setPublishedYear(dto.getPublishedYear());
        return book;
    }

    public static BookResponseDto toResponse(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setStockQuantity(book.getStockQuantity());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setCategory(book.getCategory());
        dto.setIsbn(book.getIsbn().value());
        dto.setPublisher(book.getPublisher());
        dto.setPublishedYear(book.getPublishedYear());
        return dto;
    }

    public static SimpleBookResponse toSimpleResponse(Book book) {
        SimpleBookResponse dto = new SimpleBookResponse();
        dto.setId(book.getId());
        dto.setStockQuantity(book.getStockQuantity());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setCategory(book.getCategory());
        return dto;
    }

    public static BookAudityResponseDto toAudityResponse(Book book) {
        return new BookAudityResponseDto(
                book.getId(), book.getStockQuantity(), book.getTitle(), book.getAuthor(),
                book.getCategory(), book.getIsbn().value(), book.getPublisher(), book.getPublishedYear(),
                book.getCreatedAt(), book.getUpdatedAt(), book.getDeletedAt());
    }
}
