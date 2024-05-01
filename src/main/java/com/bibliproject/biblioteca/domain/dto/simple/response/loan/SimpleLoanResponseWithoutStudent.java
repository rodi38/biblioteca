package com.bibliproject.biblioteca.domain.dto.simple.response.loan;


import com.bibliproject.biblioteca.domain.dto.simple.response.book.SimpleBookResponse;
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
public class SimpleLoanResponseWithoutStudent {
    private long id;
    private SimpleBookResponse book;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime loanDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime returnDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime limitDate;
}
