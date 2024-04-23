package com.bibliproject.biblioteca.exception.book;

public class BookOutOfStockException extends RuntimeException{
    public BookOutOfStockException() {
        super("Livro fora de estoque");
    }
}
