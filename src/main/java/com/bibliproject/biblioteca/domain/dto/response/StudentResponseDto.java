package com.bibliproject.biblioteca.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponseDto {
    private Long id;

    private String fullName;

    private String email;

    private List<LoanResponseDto> loans;
}
