package com.bibliproject.biblioteca.domain.dto.response.audity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BookAudityResponseDto(
        Long id, int stockQuantity, String title,
        String author, String category, String isbn,
        String publisher, int publishedYear, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deletedAt) {

}