package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.simple.response.loan.SimpleLoanResponse;
import com.bibliproject.biblioteca.domain.dto.simple.response.loan.SimpleLoanResponseStudent;
import com.bibliproject.biblioteca.domain.dto.simple.response.loan.SimpleLoanResponseWithoutStudent;
import com.bibliproject.biblioteca.domain.entity.Loan;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LoanMapper {




    public static SimpleLoanResponse toSimpleLoanResponse(Loan loan) {

        SimpleLoanResponse simpleLoanResponse = new SimpleLoanResponse();
        simpleLoanResponse.setId(loan.getId());
        simpleLoanResponse.setBook(BookMapper.toDtoResponse(loan.getBook()));
        simpleLoanResponse.setStudent(StudentMapper.toSimpleStudentResponse(loan.getStudent()));
        simpleLoanResponse.setLoanDate(loan.getLoanDate());
        simpleLoanResponse.setReturnDate(loan.getReturnDate());
        simpleLoanResponse.setLimitDate(loan.getLimitDate());


        return simpleLoanResponse;
    }



    public static Loan simpleLoanResponseToEntity(SimpleLoanResponse simpleLoanResponseDto) {
        Loan loan = new Loan();

        loan.setId(simpleLoanResponseDto.getId());
        loan.setLoanDate(simpleLoanResponseDto.getLoanDate());
        loan.setReturnDate(simpleLoanResponseDto.getReturnDate());
        loan.setLimitDate(simpleLoanResponseDto.getLimitDate());
        loan.setBook(BookMapper.toEntity(simpleLoanResponseDto.getBook()));
        loan.setStudent(StudentMapper.simpleStudentResponseToEntity(simpleLoanResponseDto.getStudent()));

        return loan;
    }

    public static List<Loan> simpleLoanResponseToEntity (List<SimpleLoanResponse> loanResponseDtoList) {
        if (loanResponseDtoList == null) {
            return null;
        }
        List<Loan> loanList = new ArrayList<>();

        for (SimpleLoanResponse loanResponseDto: loanResponseDtoList) {
            loanList.add(simpleLoanResponseToEntity(loanResponseDto));
        }

        return loanList;
    }

    //.
    public static Loan simpleLoanResponseWithoutStudentToEntity(SimpleLoanResponseWithoutStudent simpleLoanResponseWithoutStudent) {
        Loan loan = new Loan();

        loan.setId(simpleLoanResponseWithoutStudent.getId());
        loan.setLoanDate(simpleLoanResponseWithoutStudent.getLoanDate());
        loan.setReturnDate(simpleLoanResponseWithoutStudent.getReturnDate());
        loan.setLimitDate(simpleLoanResponseWithoutStudent.getLimitDate());
        loan.setBook(BookMapper.simpleBookResponseToEntity(simpleLoanResponseWithoutStudent.getBook()));
        loan.setLimitDate(simpleLoanResponseWithoutStudent.getLimitDate());

        return loan;
    }

    public static List<Loan> simpleLoanResponseWithoutStudentListToEntityList (List<SimpleLoanResponseWithoutStudent> simpleLoanResponseWithoutStudentDtoList) {
        if (simpleLoanResponseWithoutStudentDtoList == null) {
            return null;
        }
        List<Loan> loanList = new ArrayList<>();

        for (SimpleLoanResponseWithoutStudent loanResponseDto: simpleLoanResponseWithoutStudentDtoList) {
            loanList.add(simpleLoanResponseWithoutStudentToEntity(loanResponseDto));
        }

        return loanList;
    }

    public static List < SimpleLoanResponse > toSimpleLoanResponseList(List < Loan > loanList) {
        if (loanList == null) {
            return null;
        }
        List<SimpleLoanResponse> simpleLoanResponses = new ArrayList<>();

        for (Loan loan: loanList) {
            simpleLoanResponses.add(toSimpleLoanResponse(loan));
        }

        return simpleLoanResponses;
    }

    public static SimpleLoanResponseWithoutStudent toSimpleLoanResponseWithoutStudentDto(Loan loan) {
        SimpleLoanResponseWithoutStudent simpleLoanResponseToStudent = new SimpleLoanResponseWithoutStudent();

        simpleLoanResponseToStudent.setId(loan.getId());
        simpleLoanResponseToStudent.setBook(BookMapper.toSimpleBookResponse(loan.getBook()));
        simpleLoanResponseToStudent.setLoanDate(loan.getLoanDate());
        simpleLoanResponseToStudent.setReturnDate(loan.getReturnDate());
        simpleLoanResponseToStudent.setLimitDate(loan.getLimitDate());

        return simpleLoanResponseToStudent;
    }

    public static List<SimpleLoanResponseWithoutStudent> toSimpleLoanResponseWithoutStudentDtoList(List<Loan> loanList) {
        if (loanList == null) {
            return null;
        }
        List <SimpleLoanResponseWithoutStudent> simpleLoanListResponseToStudent = new ArrayList < > ();

        for (Loan loan: loanList) {
            simpleLoanListResponseToStudent.add(toSimpleLoanResponseWithoutStudentDto(loan));
        }

        return simpleLoanListResponseToStudent;
    }




    //


    public static SimpleLoanResponseStudent toSimpleLoanResponseStudent(Loan loan) {

        SimpleLoanResponseStudent simpleLoanResponseStudent = new SimpleLoanResponseStudent();
        simpleLoanResponseStudent.setId(loan.getId());
        simpleLoanResponseStudent.setBook(BookMapper.toDtoResponse(loan.getBook()));
        simpleLoanResponseStudent.setStudent(StudentMapper.toSimpleStudentResponseWithoutLoans(loan.getStudent()));
        simpleLoanResponseStudent.setLoanDate(loan.getLoanDate());
        simpleLoanResponseStudent.setReturnDate(loan.getReturnDate());
        simpleLoanResponseStudent.setLimitDate(loan.getLimitDate());


        return simpleLoanResponseStudent;
    }




    public static List<SimpleLoanResponseStudent> toSimpleLoanResponseStudentList(List<Loan> loanList) {
        if (loanList == null) {
            return null;
        }
        List <SimpleLoanResponseStudent> simpleLoanResponseStudentList = new ArrayList < > ();

        for (Loan loan: loanList) {
            simpleLoanResponseStudentList.add(toSimpleLoanResponseStudent(loan));
        }

        return simpleLoanResponseStudentList;
    }
}