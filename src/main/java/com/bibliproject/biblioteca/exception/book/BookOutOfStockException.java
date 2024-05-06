package com.bibliproject.biblioteca.exception.book;

public class BookOutOfStockException extends RuntimeException{
    public BookOutOfStockException(String message) {
        super(message);
    }
}
