package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.domain.dto.request.BookForRequestPlus;
import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
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
    public ResponseEntity<List<BookResponseDto>> findAll() {
        List<BookResponseDto> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }


    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequest) {
        BookResponseDto bookDTO = bookService.createBook(bookRequest);
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@RequestBody BookForRequestPlus bookRequestDto, @PathVariable Long id) {
        BookResponseDto bookResponseDto = bookService.updateBook(id, bookRequestDto);

        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable Long id) {
        BookResponseDto bookResponseDto = bookService.findById(id);
        return ResponseEntity.ok(bookResponseDto);
    }


}
