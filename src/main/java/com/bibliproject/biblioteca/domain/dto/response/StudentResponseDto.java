package com.bibliproject.biblioteca.domain.dto.response;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentResponseDto {
    private Long id;

    private String fullName;

    private String email;

    private List<LoanResponseDto> loans;
}
