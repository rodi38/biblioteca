package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.loan.CreateLoanUseCase;
import com.bibliproject.biblioteca.application.loan.DeleteLoanUseCase;
import com.bibliproject.biblioteca.application.loan.FindLoanUseCase;
import com.bibliproject.biblioteca.application.loan.ListDeletedLoansUseCase;
import com.bibliproject.biblioteca.application.loan.ListLoansUseCase;
import com.bibliproject.biblioteca.application.loan.ReturnLoanUseCase;
import com.bibliproject.biblioteca.interfaces.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.interfaces.dto.response.CustomResponse;
import com.bibliproject.biblioteca.interfaces.mapper.LoanDtoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
@AllArgsConstructor
public class LoanController {

    private final ListLoansUseCase listLoansUseCase;
    private final ListDeletedLoansUseCase listDeletedLoansUseCase;
    private final FindLoanUseCase findLoanUseCase;
    private final CreateLoanUseCase createLoanUseCase;
    private final ReturnLoanUseCase returnLoanUseCase;
    private final DeleteLoanUseCase deleteLoanUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody LoanRequestDto loanRequestDto) {
        CustomResponse response = new CustomResponse(true, "Loan created successfully.",
                LoanDtoMapper.toSimpleResponse(createLoanUseCase.execute(loanRequestDto.getBookId(), loanRequestDto.getStudentId())));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> findAllNotDeleted(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all loans.",
                listLoansUseCase.execute(search, pageable).map(LoanDtoMapper::toSimpleResponseStudent));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/audit")
    public ResponseEntity<CustomResponse> findAllDeleted(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size,
                                                          @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all loans.",
                listDeletedLoansUseCase.execute(search, pageable).map(LoanDtoMapper::toAudityResponse));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> update(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully returned the book.",
                LoanDtoMapper.toSimpleResponse(returnLoanUseCase.execute(id)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> findById(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully get the loan",
                LoanDtoMapper.toSimpleResponse(findLoanUseCase.execute(id)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteLoanUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
