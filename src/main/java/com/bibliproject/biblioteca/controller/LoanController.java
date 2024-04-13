package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private LoanService loanService;

    public ResponseEntity<BookDto> create(@RequestBody BookDto request) {
        return ResponseEntity.ok(request);
    }

}
