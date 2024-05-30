package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CustomResponse;
import com.bibliproject.biblioteca.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private BookService bookService;


    //separar findAll delete do findAll not delete
    @GetMapping
    public ResponseEntity <CustomResponse> findAllNotDeleted(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(required = false) String search, @RequestParam(required = false, defaultValue = "false") Boolean audit ) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all books", bookService.findAllNotDeleted(search, pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/audit")
    public ResponseEntity <CustomResponse> findAllDeleted(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(required = false) String search ) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all books", bookService.findAllDeleted(search, pageable));
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}