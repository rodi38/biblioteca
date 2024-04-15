package com.bibliproject.biblioteca.domain.dto.request;


import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDto {

    private String fullName;

    private String email;

    private List<LoanResponseDto> loans;
}
