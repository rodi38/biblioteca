package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CustomResponse;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleLoanResponse;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity <CustomResponse> create(@RequestBody LoanRequestDto loanRequestDto) {
        CustomResponse response = loanService.create(loanRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity < List < SimpleLoanResponse >> findAll() {
        List < SimpleLoanResponse > loanResponseDto = loanService.findAll();
        return ResponseEntity.ok(loanResponseDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity < SimpleLoanResponse > update(@RequestBody LoanRequestDto loanRequestDto, @PathVariable Long id) {
//
//        SimpleLoanResponse loanResponseDto = loanService.update(id, loanRequestDto);
//
//        return ResponseEntity.ok(loanResponseDto);
//    }

    @GetMapping("/{id}")
    public ResponseEntity < SimpleLoanResponse > findById(@PathVariable Long id) {
        SimpleLoanResponse loanResponseDto = loanService.findById(id);
        return ResponseEntity.ok(loanResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = loanService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }

}