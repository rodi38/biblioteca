package com.bibliproject.biblioteca.domain.dto.simple.response;


import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleLoanResponse {
    private long id;
    private BookResponseDto book;
    private SimpleStudentResponse student;
    private Date loanDate;
    private Date returnDate;
    private Date limitDate;
}
