package com.bibliproject.biblioteca.exception.book;

public class BookCurrentlyLoanedException extends RuntimeException {
    public BookCurrentlyLoanedException(String message) {
        super(message);
    }
}
