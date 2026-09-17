package com.bibliproject.biblioteca.interfaces.mapper;

import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.interfaces.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.interfaces.dto.response.audity.StudentAudityResponseDto;
import com.bibliproject.biblioteca.interfaces.dto.simple.response.student.SimpleStudentResponse;
import com.bibliproject.biblioteca.interfaces.dto.simple.response.student.SimpleStudentResponseWithoutLoans;

public final class StudentDtoMapper {

    private StudentDtoMapper() {
    }

    public static Student toDomain(StudentRequestDto dto) {
        Student student = new Student();
        student.setFullName(dto.getFullName());
        student.setEmail(new Email(dto.getEmail()));
        return student;
    }

    public static SimpleStudentResponse toSimpleResponse(Student student) {
        SimpleStudentResponse dto = new SimpleStudentResponse();
        dto.setId(student.getId());
        dto.setFullName(student.getFullName());
        dto.setEmail(student.getEmail().value());
        dto.setLoans(student.getLoans() == null ? null
                : student.getLoans().stream().map(LoanDtoMapper::toSimpleResponseWithoutStudent).toList());
        dto.setBorrowedBooksCount(student.getBorrowedBooksCount());
        return dto;
    }

    public static SimpleStudentResponseWithoutLoans toSimpleResponseWithoutLoans(Student student) {
        SimpleStudentResponseWithoutLoans dto = new SimpleStudentResponseWithoutLoans();
        dto.setId(student.getId());
        dto.setFullName(student.getFullName());
        dto.setEmail(student.getEmail().value());
        dto.setBorrowedBooksCount(student.getBorrowedBooksCount());
        return dto;
    }

    public static StudentAudityResponseDto toAudityResponse(Student student) {
        return new StudentAudityResponseDto(
                student.getId(), student.getFullName(), student.getEmail().value(),
                student.getLoans() == null ? null
                        : student.getLoans().stream().map(LoanDtoMapper::toAudityResponseWithoutStudent).toList(),
                student.getCreatedAt(), student.getUpdatedAt(), student.getDeletedAt());
    }
}
