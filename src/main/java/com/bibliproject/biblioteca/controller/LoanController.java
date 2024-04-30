package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CustomResponse;
import com.bibliproject.biblioteca.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity <Object> create(@RequestBody LoanRequestDto loanRequestDto) {
        CustomResponse response = new CustomResponse(true, "Loan created successfully.", loanService.create(loanRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity <CustomResponse> findAll() {
        CustomResponse response = new CustomResponse(true, "Successfully get all loans.", loanService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity < CustomResponse > update(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully returned the book.", loanService.update(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity < CustomResponse > findById(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully get the loan", loanService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "The loan has been successfully deleted.", loanService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}