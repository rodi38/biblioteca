package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public ResponseEntity<BookDto> createBook(@RequestBody BookDto request) {
        BookDto bookDTO = bookService.createBook(request);
        return ResponseEntity.ok(request);
    }

}
