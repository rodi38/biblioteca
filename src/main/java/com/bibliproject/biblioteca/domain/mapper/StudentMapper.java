package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    public static Student toEntityWithoutLoans(StudentResponseDto studentResponseDto) {
        Student student = new Student();

        student.setId(studentResponseDto.getId());
        student.setFullName(studentResponseDto.getFullName());
        student.setEmail(studentResponseDto.getEmail());

        return student;

    }
    public static StudentResponseDto toDtoWithoutLoans(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setFullName(student.getFullName());
        studentResponseDto.setEmail(student.getEmail());

        return studentResponseDto;
    }

    public static List < StudentResponseDto > toDtoListWithoutLoans(List < Student > student) {
        if (student == null) {
            return null;
        }

        List < StudentResponseDto > studentResponseDtoList = new ArrayList < > ();

        for (Student studentResponseDtoList1: student) {
            studentResponseDtoList.add(toDtoWithoutLoans(studentResponseDtoList1));
        }

        return studentResponseDtoList;
    }

    public static List < Student > toEntityListWithoutLoans(List < StudentResponseDto > studentResponseDtoList) {
        if (studentResponseDtoList == null) {
            return null;
        }
        List < Student > studentList = new ArrayList < > ();

        for (StudentResponseDto studentResponseDtoList1: studentResponseDtoList) {
            studentList.add(toEntityWithoutLoans(studentResponseDtoList1));
        }

        return studentList;
    }

    // with loans
    public static Student toEntity(StudentResponseDto studentResponseDto) {
        Student student = new Student();

        student.setId(studentResponseDto.getId());
        student.setFullName(studentResponseDto.getFullName());
        student.setEmail(studentResponseDto.getEmail());
        student.setLoans(LoanMapper.toEntityList(studentResponseDto.getLoans()));

        return student;

    }
    public static StudentResponseDto toDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setFullName(student.getFullName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setLoans(LoanMapper.toDtoList(student.getLoans()));

        return studentResponseDto;
    }

    public static Student dtoRequestToEntity(StudentRequestDto studentRequestDto) {
        Student student = new Student();

        student.setFullName(studentRequestDto.getFullName());
        student.setEmail(studentRequestDto.getEmail());
        student.setLoans(LoanMapper.toEntityList(studentRequestDto.getLoans()));
        return student;
    }
    public static StudentRequestDto entityToDtoRequest(Student student) {
        StudentRequestDto studentRequestDto = new StudentRequestDto();

        studentRequestDto.setFullName(student.getFullName());
        studentRequestDto.setEmail(student.getEmail());
        studentRequestDto.setLoans(LoanMapper.toDtoList(student.getLoans()));
        return studentRequestDto;
    }

    public static List < StudentResponseDto > toDtoList(List < Student > student) {
        if (student == null) {
            return null;
        }
        List < StudentResponseDto > studentResponseDtoList = new ArrayList < > ();

        for (Student studentResponseDtoList1: student) {
            studentResponseDtoList.add(toDto(studentResponseDtoList1));
        }

        return studentResponseDtoList;
    }

    public static List < Student > toEntityList(List < StudentResponseDto > studentResponseDtoList) {
        List < Student > studentList = new ArrayList < > ();

        for (StudentResponseDto studentResponseDtoList1: studentResponseDtoList) {
            studentList.add(toEntity(studentResponseDtoList1));
        }

        return studentList;
    }

}