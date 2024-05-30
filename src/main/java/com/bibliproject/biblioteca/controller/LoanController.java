package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CustomResponse;
import com.bibliproject.biblioteca.service.LoanService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity <CustomResponse> findAllNotDeleted(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all loans.", loanService.findAllNotDeleted(search, pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/audit")
    public ResponseEntity <CustomResponse> findAllDeleted(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all loans.", loanService.findAllDeleted(search, pageable));
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}