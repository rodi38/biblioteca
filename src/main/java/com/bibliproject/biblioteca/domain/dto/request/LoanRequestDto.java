package com.bibliproject.biblioteca.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanRequestDto {

    private BookDto book;

    private Date loanDate;

    private Date returnDate;
}
