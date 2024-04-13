package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private BookService bookService;


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
