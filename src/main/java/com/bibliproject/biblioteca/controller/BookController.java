package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private BookService bookService;



    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        List<BookDto> bookDTO = bookService.findAll();
        return ResponseEntity.ok(bookDTO);
    }


    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto book) {
        BookDto bookDTO = bookService.createBook(book);
        return ResponseEntity.ok(bookDTO);
    }

    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto book) {
        BookDto bookDTO = bookService.updateBook(book);

        return ResponseEntity.ok(book);
    }

}
