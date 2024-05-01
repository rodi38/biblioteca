package com.bibliproject.biblioteca.domain.dto.simple.response.student;


import com.bibliproject.biblioteca.domain.dto.simple.response.loan.SimpleLoanResponseWithoutStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleStudentResponse {

    private Long id;
    private String fullName;
    private String email;
    private List<SimpleLoanResponseWithoutStudent> loans;
    private int borrowedBooksCount;

}
