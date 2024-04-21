package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleStudentResponse;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    public static Student dtoRequestToEntity(StudentRequestDto requestDto) {
        Student student = new Student();

        student.setFullName(requestDto.getFullName());
        student.setEmail(requestDto.getEmail());
        return student;
    }


    public static SimpleStudentResponse toSimpleStudentResponse(Student student) {
        SimpleStudentResponse simpleStudentResponse = new SimpleStudentResponse();

        simpleStudentResponse.setId(student.getId());
        simpleStudentResponse.setFullName(student.getFullName());
        simpleStudentResponse.setEmail(student.getEmail());
        simpleStudentResponse.setLoans(LoanMapper.toSimpleLoanResponseWithoutStudentDtoList(student.getLoans()));

        return simpleStudentResponse;
    }

    public static Student simpleStudentResponseToEntity(SimpleStudentResponse studentResponse) {
        Student student = new Student();

        student.setId(studentResponse.getId());
        student.setFullName(studentResponse.getFullName());
        student.setEmail(studentResponse.getEmail());
        student.setLoans(LoanMapper.simpleLoanResponseWithoutStudentListToEntityList(studentResponse.getLoans()));

        return student;
    }

    public static List<Student> simpleStudentResponseListToEntityList(List<SimpleStudentResponse> studentResponseList) {
        if (studentResponseList == null) {
            return null;
        }

        List<Student> studentList = new ArrayList<>();

        for (SimpleStudentResponse studentResponseDtoList1 : studentResponseList) {
            studentList.add(simpleStudentResponseToEntity(studentResponseDtoList1));
        }

        return studentList;
    }

    public static List<SimpleStudentResponse> toSimpleStudentResponseList(List<Student> studentList) {
        if (studentList == null) {
            return null;
        }

        List<SimpleStudentResponse> simpleStudentResponseList = new ArrayList<>();

        for (Student studentResponseDtoList1 : studentList) {
            simpleStudentResponseList.add(toSimpleStudentResponse(studentResponseDtoList1));
        }

        return simpleStudentResponseList;
    }



}