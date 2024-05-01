package com.bibliproject.biblioteca.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;
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

    private LocalDateTime loanDate;

    private LocalDateTime returnDate;
    private LocalDateTime limitDate;

}