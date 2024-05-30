package com.bibliproject.biblioteca.domain.dto.response.audity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record LoanAudityResponseDto(
        Long id, BookAudityResponseDto book, StudentAudityResponseDto student,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime loanDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime returnDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deletedAt ) {
}
