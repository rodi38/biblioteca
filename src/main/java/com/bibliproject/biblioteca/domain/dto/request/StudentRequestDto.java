package com.bibliproject.biblioteca.domain.dto.request;


import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
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

    private List<LoanResponseDto> loans;
}
