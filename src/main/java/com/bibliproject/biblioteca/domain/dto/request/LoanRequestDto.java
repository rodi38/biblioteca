package com.bibliproject.biblioteca.domain.dto.request;


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
    private Date returnDate;
    private Date limitDate;

}