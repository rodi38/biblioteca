package com.bibliproject.biblioteca.infrastructure.persistence.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.Isbn;

public final class BookPersistenceMapper {

    private BookPersistenceMapper() {
    }

    public static Book toDomain(BookJpaEntity entity) {
        return new Book(
                entity.getId(),
                entity.getStockQuantity(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getCategory(),
                new Isbn(entity.getIsbn()),
                entity.getPublisher(),
                entity.getPublishedYear(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt(),
                entity.isDeleted()
        );
    }

    public static BookJpaEntity toJpaEntity(Book book) {
        BookJpaEntity entity = new BookJpaEntity();
        entity.setId(book.getId());
        entity.setStockQuantity(book.getStockQuantity());
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setCategory(book.getCategory());
        entity.setIsbn(book.getIsbn().value());
        entity.setPublisher(book.getPublisher());
        entity.setPublishedYear(book.getPublishedYear());
        entity.setCreatedAt(book.getCreatedAt());
        entity.setUpdatedAt(book.getUpdatedAt());
        entity.setDeletedAt(book.getDeletedAt());
        entity.setDeleted(book.isDeleted());
        return entity;
    }
}
