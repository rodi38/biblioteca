package com.bibliproject.biblioteca.domain.dto.simple.response.loan;


import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.student.SimpleStudentResponseWithoutLoans;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleLoanResponseStudent {
    private long id;
    private BookResponseDto book;
    private SimpleStudentResponseWithoutLoans student;
    private Date loanDate;
    private Date returnDate;
    private Date limitDate;
}
