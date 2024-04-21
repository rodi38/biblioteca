package com.bibliproject.biblioteca.domain.dto.response;

import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleLoanResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private boolean success;
    private String message;
    private SimpleLoanResponse data;

}
