package com.bibliproject.biblioteca.domain.dto.response.audity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record StudentAudityResponseDto(
        Long id, String fullName, String email, List<LoanAudityResponseDto> loans,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deletedAt
) {
}
