package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CustomResponse;
import com.bibliproject.biblioteca.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping
    public ResponseEntity <CustomResponse> findAll() {
        CustomResponse response = new CustomResponse(true, "Successfully get all books", bookService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity < CustomResponse > createBook(@RequestBody BookRequestDto bookRequestDto) {
        CustomResponse response = new CustomResponse(true, "Successfully created the book", bookService.createBook(bookRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity < CustomResponse > updateBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Book has been updated", bookService.updateBook(id, bookRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity < CustomResponse > findById(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully get the book", bookService.findById(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteBook(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully deleted the book", bookService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}