package com.bibliproject.biblioteca.exception.book;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id) {
        super("Não foi possivel encontrar o livro com id: " + id);
    }
}
