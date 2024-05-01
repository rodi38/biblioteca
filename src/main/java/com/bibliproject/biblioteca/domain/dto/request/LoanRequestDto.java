package com.bibliproject.biblioteca.domain.dto.request;


import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanRequestDto {

    private Long bookId;
    private Long studentId;
    private LocalDateTime returnDate;
    private LocalDateTime limitDate;

}