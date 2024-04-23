package com.bibliproject.biblioteca.domain.dto.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentRequestDto {

    private String fullName;
    private String email;

}