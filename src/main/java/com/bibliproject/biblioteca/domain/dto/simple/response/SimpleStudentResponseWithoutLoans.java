package com.bibliproject.biblioteca.domain.dto.simple.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
