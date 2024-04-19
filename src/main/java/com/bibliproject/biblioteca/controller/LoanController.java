package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleLoanResponse;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.service.LoanService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity <SimpleLoanResponse> create(@RequestBody LoanRequestDto loanRequestDto) {
        SimpleLoanResponse loanResponseDto = loanService.create(loanRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanResponseDto);
    }

    @GetMapping
    public ResponseEntity < List < LoanResponseDto >> findAll() {
        List < LoanResponseDto > loanResponseDto = loanService.findAll();
        return ResponseEntity.ok(loanResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity < LoanResponseDto > update(@RequestBody LoanRequestDto loanRequestDto, @PathVariable Long id) {

        LoanResponseDto loanResponseDto = loanService.update(id, loanRequestDto);

        return ResponseEntity.ok(loanResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity < LoanResponseDto > findById(@PathVariable Long id) {
        LoanResponseDto loanResponseDto = loanService.findById(id);
        return ResponseEntity.ok(loanResponseDto);
    }

}