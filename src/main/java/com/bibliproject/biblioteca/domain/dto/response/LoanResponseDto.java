package com.bibliproject.biblioteca.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanResponseDto {

    private Long id;

    private BookResponseDto book;

    private StudentResponseDto student;

    private Date loanDate;

    private Date returnDate;
}

