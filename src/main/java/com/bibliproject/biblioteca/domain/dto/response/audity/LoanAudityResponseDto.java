package com.bibliproject.biblioteca.domain.dto.response.audity;

import java.time.LocalDateTime;

public record LoanAudityResponseDto(
        Long id, BookAudityResponseDto book, StudentAudityResponseDto student,
        LocalDateTime loanDate, LocalDateTime returnDate,  LocalDateTime createdAt,
        LocalDateTime updatedAt, LocalDateTime deletedAt ) {
}
