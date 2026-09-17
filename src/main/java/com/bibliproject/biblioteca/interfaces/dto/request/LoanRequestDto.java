package com.bibliproject.biblioteca.interfaces.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanRequestDto {

    @NotNull(message = "O livro é obrigatório")
    private Long bookId;

    @NotNull(message = "O estudante é obrigatório")
    private Long studentId;

    private LocalDateTime returnDate;
    private LocalDateTime limitDate;
}
