package com.bibliproject.biblioteca.exception;


import com.bibliproject.biblioteca.exception.book.BookAlreadyReturnedException;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import com.bibliproject.biblioteca.exception.book.BookOutOfStockException;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import com.bibliproject.biblioteca.exception.loan.LoanOverdueException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<Object> bookNotFoundExceptionHandling(BookNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {LoanNotFoundException.class})
    public ResponseEntity<Object> loanNotFoundExceptionHandling(LoanNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {StudentNotFoundException.class})
    public ResponseEntity<Object> studentNotFoundExceptionHandling(StudentNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BookOutOfStockException.class})
    public ResponseEntity<Object> bookOutOfStockExceptionHandling(BookOutOfStockException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LoanOverdueException.class})
    public ResponseEntity<Object> loanOverdueExceptionHandling(LoanOverdueException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {BookAlreadyReturnedException.class})
    public ResponseEntity<Object> bookAlreadyReturnedExceptionHandling(BookAlreadyReturnedException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
