package com.bibliproject.biblioteca.domain.dto.request;

import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanRequestDto {

    private Long bookId;
    private Long studentId;
    //private Date loanDate;
    private Date returnDate;
    private Date limitDate;

}