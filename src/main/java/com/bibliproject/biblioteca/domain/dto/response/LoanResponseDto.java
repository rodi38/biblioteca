package com.bibliproject.biblioteca.domain.dto.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanResponseDto {

    private Long id;

    private BookResponseDto book;

    private StudentResponseDto student;

    private Date loanDate;

    private Date returnDate;
}

