package com.bibliproject.biblioteca.domain.dto.request;

import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.entity.Loan;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentRequestDto {

    private String fullName;
    private String email;

}