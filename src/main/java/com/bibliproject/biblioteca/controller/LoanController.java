package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.domain.dto.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.LoanResponseDto;
import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.LoanResponseDto;
import com.bibliproject.biblioteca.service.LoanService;
import com.bibliproject.biblioteca.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;


    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    public ResponseEntity<LoanResponseDto> create(@RequestBody LoanRequestDto loanRequestDto) {
        LoanResponseDto loanResponseDto = loanService.create(loanRequestDto);
        return ResponseEntity.ok(loanResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDto>> findAll() {
        List<LoanResponseDto> loanResponseDto = loanService.findAll();
        return ResponseEntity.ok(loanResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponseDto> update(@RequestBody LoanRequestDto loanRequestDto, @PathVariable Long id) {

        LoanResponseDto loanResponseDto = loanService.update(id, loanRequestDto);

        return ResponseEntity.ok(loanResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDto> findById(@PathVariable Long id) {
        LoanResponseDto loanResponseDto = loanService.findById(id);
        return ResponseEntity.ok(loanResponseDto);
    }

}
