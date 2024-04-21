package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping
    public ResponseEntity < List < BookResponseDto >> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity < BookResponseDto > createBook(@RequestBody BookRequestDto bookRequest) {
        BookResponseDto bookDTO = bookService.createBook(bookRequest);
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity < BookResponseDto > updateBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable Long id) {
        BookResponseDto bookResponseDto = bookService.updateBook(id, bookRequestDto);

        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity < BookResponseDto > findById(@PathVariable Long id) {
        BookResponseDto bookResponseDto = bookService.findById(id);
        return ResponseEntity.ok(bookResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Long id) {
        Boolean isDeleted = bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }

}