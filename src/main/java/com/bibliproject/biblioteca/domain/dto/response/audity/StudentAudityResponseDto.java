package com.bibliproject.biblioteca.domain.dto.response.audity;

import java.time.LocalDateTime;
import java.util.List;

public record StudentAudityResponseDto(
        Long id, String fullName, String email, List<LoanAudityResponseDto> loans, int borrowedBooksCount,
        LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt
) {
}
