package com.bibliproject.biblioteca.domain.dto.simple.response.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleStudentResponseWithoutLoans {

    private Long id;
    private String fullName;
    private String email;
    private int barrowedBooksCount;

}
