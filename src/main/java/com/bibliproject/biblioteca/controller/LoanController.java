package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.BookDTO;
import com.bibliproject.biblioteca.service.BookService;
import com.bibliproject.biblioteca.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private LoanService loanService;

    public ResponseEntity<BookDTO> create(@RequestBody BookDTO request) {
        return ResponseEntity.ok(request);
    }

}
