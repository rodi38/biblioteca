package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleLoanResponse;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleLoanResponseWithoutStudent;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.service.BookService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LoanMapper {
//    private static BookService bookService;
//
//    public static Loan toEntityWithoutLoans(LoanResponseDto loanResponseDto) {
//        Loan loan = new Loan();
//
//        loan.setId(loanResponseDto.getId());
//        loan.setLoanDate(loanResponseDto.getLoanDate());
//        loan.setReturnDate(loanResponseDto.getReturnDate());
//        loan.setBook(BookMapper.toEntity(loanResponseDto.getBook()));
//        loan.setStudent(StudentMapper.toEntityWithoutLoans(loanResponseDto.getStudent()));
//        return loan;
//    }
//
    public static Loan dtoRequestToEntity(LoanRequestDto loanRequestDto) {
        Loan loan = new Loan();

        loan.setLoanDate(loanRequestDto.getLoanDate());
        loan.setReturnDate(loanRequestDto.getReturnDate());
//        loan.setBook(BookMapper.toEntity(bookRepository.findById()));
//        loan.setStudent(StudentMapper.toEntityWithoutLoans(loanRequestDto.getStudent()));
        //loan.getBook().(toEntityListWithoutLoans(loanRequestDto.getBook().getLoans()));
        //loan.getStudent().setLoans(toEntityListWithoutLoans(loanRequestDto.getStudent().getLoan()));


        return loan;
    }
//    public static LoanResponseDto toDtoWithoutLoans(Loan loan) {
//        LoanResponseDto loanResponseDto = new LoanResponseDto();
//        loanResponseDto.setId(loan.getId());
//        loanResponseDto.setLoanDate(loan.getLoanDate());
//        loanResponseDto.setReturnDate(loan.getReturnDate());
//        loanResponseDto.setBook(BookMapper.toDtoResponse(loan.getBook()));
//        loanResponseDto.setStudent(StudentMapper.toDtoWithoutLoans(loan.getStudent()));
//
//        return loanResponseDto;
//    }
//
//    public static List < Loan > toEntityListWithoutLoans(List < LoanResponseDto > loanResponseDto) {
//        if (loanResponseDto == null) {
//            return null;
//        }
//        List < Loan > loanList = new ArrayList < > ();
//
//        for (LoanResponseDto loanResponseDto1: loanResponseDto) {
//            loanList.add(toEntityWithoutLoans(loanResponseDto1));
//        }
//
//        return loanList;
//    }
//    public static List < LoanResponseDto > toDtoListWithoutLoans(List < Loan > loanList) {
//        if (loanList == null) {
//            return null;
//        }
//        List < LoanResponseDto > loanResponseDtoList = new ArrayList < > ();
//
//        for (Loan loan: loanList) {
//            loanResponseDtoList.add(toDtoWithoutLoans(loan));
//        }
//
//        return loanResponseDtoList;
//    }





    public static SimpleLoanResponse toSimpleLoanResponse(Loan loan) {

        SimpleLoanResponse simpleLoanResponse = new SimpleLoanResponse();
        simpleLoanResponse.setId(loan.getId());
        simpleLoanResponse.setBook(BookMapper.toSimpleBookResponse(loan.getBook()));
        simpleLoanResponse.setStudent(StudentMapper.toSimpleStudentResponse(loan.getStudent()));
        simpleLoanResponse.setLoanDate(loan.getLoanDate());
        simpleLoanResponse.setReturnDate(loan.getReturnDate());


        return simpleLoanResponse;
    }

    public static Loan simpleLoanResponseToEntity(SimpleLoanResponse simpleLoanResponseDto) {
        Loan loan = new Loan();

        loan.setId(simpleLoanResponseDto.getId());
        loan.setLoanDate(simpleLoanResponseDto.getLoanDate());
        loan.setReturnDate(simpleLoanResponseDto.getReturnDate());
        loan.setBook(BookMapper.simpleBookResponseToEntity(simpleLoanResponseDto.getBook()));
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
        loan.setBook(BookMapper.simpleBookResponseToEntity(simpleLoanResponseWithoutStudent.getBook()));

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


    //with loans

    //
//    public static Loan toEntity(LoanResponseDto loanResponseDto) {
//        Loan loan = new Loan();
//
//        loan.setId(loanResponseDto.getId());
//        loan.setLoanDate(loanResponseDto.getLoanDate());
//        loan.setReturnDate(loanResponseDto.getReturnDate());
//        loan.setBook(BookMapper.toEntity(loanResponseDto.getBook()));
//        loan.setStudent(StudentMapper.toEntity(loanResponseDto.getStudent()));
//
//        return loan;
//    }
//
//    public static LoanResponseDto toDto(Loan loan) {
//        LoanResponseDto loanResponseDto = new LoanResponseDto();
//        loanResponseDto.setId(loan.getId());
//        loanResponseDto.setLoanDate(loan.getLoanDate());
//        loanResponseDto.setReturnDate(loan.getReturnDate());
//        loanResponseDto.setBook(BookMapper.toDto(loan.getBook()));
//        loanResponseDto.setStudent(StudentMapper.toDto(loan.getStudent()));
//        return loanResponseDto;
//    }
//
//    public static List < Loan > toEntityList(List < LoanResponseDto > loanResponseDto) {
//        if (loanResponseDto == null) {
//            return null;
//        }
//        List < Loan > loanList = new ArrayList < > ();
//
//        for (LoanResponseDto loanResponseDto1: loanResponseDto) {
//            loanList.add(toEntity(loanResponseDto1));
//        }
//
//        return loanList;
//    }
//    public static List < LoanResponseDto > toDtoList(List < Loan > loanList) {
//        if (loanList == null) {
//            return null;
//        }
//        List < LoanResponseDto > loanResponseDtoList = new ArrayList < > ();
//
//        for (Loan loan: loanList) {
//            loanResponseDtoList.add(toDto(loan));
//        }
//
//        return loanResponseDtoList;
//    }
//
}