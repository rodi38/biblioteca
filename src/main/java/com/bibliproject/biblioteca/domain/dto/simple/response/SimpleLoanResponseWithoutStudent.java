package com.bibliproject.biblioteca.domain.dto.simple.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleLoanResponseWithoutStudent {
    private long id;
    private SimpleBookResponse book;
    private Date loanDate;
    private Date returnDate;
}
