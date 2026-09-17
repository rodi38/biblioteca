package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.book.CreateBookUseCase;
import com.bibliproject.biblioteca.application.book.DeleteBookUseCase;
import com.bibliproject.biblioteca.application.book.FindBookUseCase;
import com.bibliproject.biblioteca.application.book.ListBooksUseCase;
import com.bibliproject.biblioteca.application.book.ListDeletedBooksUseCase;
import com.bibliproject.biblioteca.application.book.UpdateBookUseCase;
import com.bibliproject.biblioteca.domain.book.Isbn;
import com.bibliproject.biblioteca.interfaces.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.interfaces.dto.response.CustomResponse;
import com.bibliproject.biblioteca.interfaces.mapper.BookDtoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final ListBooksUseCase listBooksUseCase;
    private final ListDeletedBooksUseCase listDeletedBooksUseCase;
    private final FindBookUseCase findBookUseCase;
    private final CreateBookUseCase createBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;

    @GetMapping
    public ResponseEntity<CustomResponse> findAllNotDeleted(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(required = false) String search,
                                                             @RequestParam(required = false, defaultValue = "false") Boolean audit) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all books",
                listBooksUseCase.execute(search, pageable).map(BookDtoMapper::toResponse));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/audit")
    public ResponseEntity<CustomResponse> findAllDeleted(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size,
                                                          @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all books",
                listDeletedBooksUseCase.execute(search, pageable).map(BookDtoMapper::toAudityResponse));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CustomResponse> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        CustomResponse response = new CustomResponse(true, "Successfully created the book",
                BookDtoMapper.toResponse(createBookUseCase.execute(BookDtoMapper.toDomain(bookRequestDto))));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateBook(@Valid @RequestBody BookRequestDto bookRequestDto, @PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Book has been updated",
                BookDtoMapper.toResponse(updateBookUseCase.execute(id, bookRequestDto.getTitle(), bookRequestDto.getAuthor(),
                        bookRequestDto.getCategory(), new Isbn(bookRequestDto.getIsbn()), bookRequestDto.getPublisher(),
                        bookRequestDto.getPublishedYear(), bookRequestDto.getStockQuantity())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> findById(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully get the book",
                BookDtoMapper.toResponse(findBookUseCase.execute(id)));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteBookUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
