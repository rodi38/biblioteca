package com.bibliproject.biblioteca.interfaces;

import com.bibliproject.biblioteca.exception.auth.ForbiddenOperationException;
import com.bibliproject.biblioteca.exception.book.BookAlreadyReturnedException;
import com.bibliproject.biblioteca.exception.book.BookCurrentlyLoanedException;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import com.bibliproject.biblioteca.exception.book.BookOutOfStockException;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import com.bibliproject.biblioteca.exception.loan.LoanOverdueException;
import com.bibliproject.biblioteca.exception.student.StudentBorrowLimitReachedException;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import com.bibliproject.biblioteca.exception.user.EmailAlreadyInUseException;
import com.bibliproject.biblioteca.exception.user.InvalidCredentialsException;
import com.bibliproject.biblioteca.exception.user.InvalidPasswordException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(value = {StudentHaveDebtException.class})
    public ResponseEntity<Object> studentHaveDebtExceptionHandling(StudentHaveDebtException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {StudentBorrowLimitReachedException.class})
    public ResponseEntity<Object> studentBorrowLimitReachedExceptionHandling(StudentBorrowLimitReachedException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BookCurrentlyLoanedException.class})
    public ResponseEntity<Object> bookCurrentlyLoanedExceptionHandling(BookCurrentlyLoanedException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmailAlreadyInUseException.class})
    public ResponseEntity<Object> emailAlreadyInUseExceptionHandling(EmailAlreadyInUseException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {InvalidPasswordException.class})
    public ResponseEntity<Object> invalidPasswordExceptionHandling(InvalidPasswordException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidCredentialsException.class})
    public ResponseEntity<Object> invalidCredentialsExceptionHandling(InvalidCredentialsException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ForbiddenOperationException.class})
    public ResponseEntity<Object> forbiddenOperationExceptionHandling(ForbiddenOperationException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);

        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", "Não foi possível concluir a operação: os dados violam uma restrição de integridade.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
