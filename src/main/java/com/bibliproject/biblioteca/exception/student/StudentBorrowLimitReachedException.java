package com.bibliproject.biblioteca.exception.student;

public class StudentBorrowLimitReachedException extends RuntimeException {
    public StudentBorrowLimitReachedException(String message) {
        super(message);
    }
}
