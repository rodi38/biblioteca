package com.bibliproject.biblioteca.exception.book;

public class BookAlreadyReturnedException extends RuntimeException{
    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}
