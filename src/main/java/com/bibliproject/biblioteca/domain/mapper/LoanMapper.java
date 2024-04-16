package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.entity.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanMapper {

    public static Loan toEntityWithoutLoans(LoanResponseDto loanResponseDto) {
        Loan loan = new Loan();

        loan.setId(loanResponseDto.getId());
        loan.setLoanDate(loanResponseDto.getLoanDate());
        loan.setReturnDate(loanResponseDto.getReturnDate());
        loan.setBook(BookMapper.toEntityWithoutLoans(loanResponseDto.getBook()));
        loan.setStudent(StudentMapper.toEntityWithoutLoans(loanResponseDto.getStudent()));
        return loan;
    }

    public static Loan dtoRequestToEntity(LoanRequestDto loanRequestDto) {
        Loan loan = new Loan();

        loan.setLoanDate(loanRequestDto.getLoanDate());
        loan.setReturnDate(loanRequestDto.getReturnDate());
        loan.setBook(BookMapper.toEntity(loanRequestDto.getBook()));
        loan.setStudent(StudentMapper.toEntity(loanRequestDto.getStudent()));

        return loan;
    }
    public static LoanRequestDto entityToLoanRequestDto(Loan loan) {
        LoanRequestDto loanRequestDto = new LoanRequestDto();

        loanRequestDto.setLoanDate(loan.getLoanDate());
        loanRequestDto.setReturnDate(loan.getReturnDate());
        loanRequestDto.setBook(BookMapper.toDto(loan.getBook()));
        loanRequestDto.setStudent(StudentMapper.toDto(loan.getStudent()));

        return loanRequestDto;
    }

    public static LoanResponseDto toDtoWithoutLoans(Loan loan) {
        LoanResponseDto loanResponseDto = new LoanResponseDto();
        loanResponseDto.setId(loan.getId());
        loanResponseDto.setLoanDate(loan.getLoanDate());
        loanResponseDto.setReturnDate(loan.getReturnDate());
        loanResponseDto.setBook(BookMapper.toDtoWithoutLoans(loan.getBook()));
        loanResponseDto.setStudent(StudentMapper.toDtoWithoutLoans(loan.getStudent()));

        return loanResponseDto;
    }

    public static List < Loan > toEntityListWithoutLoans(List < LoanResponseDto > loanResponseDto) {
        if (loanResponseDto == null) {
            return null;
        }
        List < Loan > loanList = new ArrayList < > ();

        for (LoanResponseDto loanResponseDto1: loanResponseDto) {
            loanList.add(toEntityWithoutLoans(loanResponseDto1));
        }

        return loanList;
    }
    public static List < LoanResponseDto > toDtoListWithoutLoans(List < Loan > loanList) {
        if (loanList == null) {
            return null;
        }
        List < LoanResponseDto > loanResponseDtoList = new ArrayList < > ();

        for (Loan loan: loanList) {
            loanResponseDtoList.add(toDtoWithoutLoans(loan));
        }

        return loanResponseDtoList;
    }

    //with loans

    //
    public static Loan toEntity(LoanResponseDto loanResponseDto) {
        Loan loan = new Loan();

        loan.setId(loanResponseDto.getId());
        loan.setLoanDate(loanResponseDto.getLoanDate());
        loan.setReturnDate(loanResponseDto.getReturnDate());
        loan.setBook(BookMapper.toEntity(loanResponseDto.getBook()));
        loan.setStudent(StudentMapper.toEntity(loanResponseDto.getStudent()));

        return loan;
    }

    public static LoanResponseDto toDto(Loan loan) {
        LoanResponseDto loanResponseDto = new LoanResponseDto();
        loanResponseDto.setId(loan.getId());
        loanResponseDto.setLoanDate(loan.getLoanDate());
        loanResponseDto.setReturnDate(loan.getReturnDate());
        loanResponseDto.setBook(BookMapper.toDto(loan.getBook()));
        loanResponseDto.setStudent(StudentMapper.toDto(loan.getStudent()));
        return loanResponseDto;
    }

    public static List < Loan > toEntityList(List < LoanResponseDto > loanResponseDto) {
        if (loanResponseDto == null) {
            return null;
        }
        List < Loan > loanList = new ArrayList < > ();

        for (LoanResponseDto loanResponseDto1: loanResponseDto) {
            loanList.add(toEntity(loanResponseDto1));
        }

        return loanList;
    }
    public static List < LoanResponseDto > toDtoList(List < Loan > loanList) {
        if (loanList == null) {
            return null;
        }
        List < LoanResponseDto > loanResponseDtoList = new ArrayList < > ();

        for (Loan loan: loanList) {
            loanResponseDtoList.add(toDto(loan));
        }

        return loanResponseDtoList;
    }

}