package com.bibliproject.biblioteca.domain.dto.simple.response.loan;


import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.student.SimpleStudentResponseWithoutLoans;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleLoanResponseStudent {
    private long id;
    private BookResponseDto book;
    private SimpleStudentResponseWithoutLoans student;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime loanDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime returnDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime limitDate;
}
