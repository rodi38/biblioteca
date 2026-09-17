package com.bibliproject.biblioteca.interfaces.mapper;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.interfaces.dto.response.audity.LoanAudityResponseDto;
import com.bibliproject.biblioteca.interfaces.dto.simple.response.loan.SimpleLoanResponse;
import com.bibliproject.biblioteca.interfaces.dto.simple.response.loan.SimpleLoanResponseStudent;
import com.bibliproject.biblioteca.interfaces.dto.simple.response.loan.SimpleLoanResponseWithoutStudent;

public final class LoanDtoMapper {

    private LoanDtoMapper() {
    }

    public static SimpleLoanResponse toSimpleResponse(Loan loan) {
        SimpleLoanResponse dto = new SimpleLoanResponse();
        dto.setId(loan.getId());
        dto.setBook(BookDtoMapper.toResponse(loan.getBook()));
        dto.setStudent(StudentDtoMapper.toSimpleResponse(loan.getStudent()));
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setLimitDate(loan.getLimitDate());
        return dto;
    }

    public static SimpleLoanResponseStudent toSimpleResponseStudent(Loan loan) {
        SimpleLoanResponseStudent dto = new SimpleLoanResponseStudent();
        dto.setId(loan.getId());
        dto.setBook(BookDtoMapper.toResponse(loan.getBook()));
        dto.setStudent(StudentDtoMapper.toSimpleResponseWithoutLoans(loan.getStudent()));
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setLimitDate(loan.getLimitDate());
        return dto;
    }

    public static SimpleLoanResponseWithoutStudent toSimpleResponseWithoutStudent(Loan loan) {
        SimpleLoanResponseWithoutStudent dto = new SimpleLoanResponseWithoutStudent();
        dto.setId(loan.getId());
        dto.setBook(BookDtoMapper.toSimpleResponse(loan.getBook()));
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setLimitDate(loan.getLimitDate());
        return dto;
    }

    public static LoanAudityResponseDto toAudityResponse(Loan loan) {
        return new LoanAudityResponseDto(
                loan.getId(), BookDtoMapper.toAudityResponse(loan.getBook()), StudentDtoMapper.toAudityResponse(loan.getStudent()),
                loan.getLoanDate(), loan.getReturnDate(), null, null, loan.getDeletedAt());
    }

    public static LoanAudityResponseDto toAudityResponseWithoutStudent(Loan loan) {
        return new LoanAudityResponseDto(
                loan.getId(), BookDtoMapper.toAudityResponse(loan.getBook()), null,
                loan.getLoanDate(), loan.getReturnDate(), null, null, loan.getDeletedAt());
    }
}
