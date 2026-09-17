package com.bibliproject.biblioteca.interfaces.dto.simple.response.loan;

import com.bibliproject.biblioteca.interfaces.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.interfaces.dto.simple.response.student.SimpleStudentResponseWithoutLoans;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
