package com.bibliproject.biblioteca.exception.loan;

public class LoanOverdueException extends RuntimeException {
    public LoanOverdueException(String message) {
        super(message);
    }
}
