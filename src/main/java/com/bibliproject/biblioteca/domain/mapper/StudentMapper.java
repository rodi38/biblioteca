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

    public static List<StudentResponseDto> toDtoListWithoutLoans(List<Student> student) {
        if (student == null) {
            return null;
        }

        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();

        for (Student studentResponseDtoList1 : student) {
            studentResponseDtoList.add(toDtoWithoutLoans(studentResponseDtoList1));
        }

        return studentResponseDtoList;
    }

    public static List<Student> toEntityListWithoutLoans(List<StudentResponseDto> studentResponseDtoList) {
        if (studentResponseDtoList == null) {
            return null;
        }
        List<Student> studentList = new ArrayList<>();

        for (StudentResponseDto studentResponseDtoList1 : studentResponseDtoList) {
            studentList.add(toEntityWithoutLoans(studentResponseDtoList1));
        }

        return studentList;
    }

    public static Student dtoRequestToEntity(StudentRequestDto requestDto) {
        Student student = new Student();

        student.setFullName(requestDto.getFullName());
        student.setEmail(requestDto.getEmail());
        //student.setLoans(LoanMapper.toEntityList(requestDto.getLoans()));
        return student;
    }

    public static StudentResponseDto addLoanToDto(Student student) {
        StudentResponseDto studentResponseDto = toDtoWithoutLoans(student);
        List<LoanResponseDto> loanResponseDtos = new ArrayList<>(LoanMapper.toDtoListWithoutLoans(student.getLoans()));
        studentResponseDto.setLoans(loanResponseDtos);
        return studentResponseDto;
    }

    public static Student addLoanToEntity(StudentResponseDto studentResponseDto) {
        Student student = toEntityWithoutLoans(studentResponseDto);
        List<Loan> students = new ArrayList<>(LoanMapper.toEntityListWithoutLoans(studentResponseDto.getLoans()));
        student.setLoans(students);
        return student;
    }

    public static SimpleStudentResponse toSimpleStudentResponse(Student student) {
        SimpleStudentResponse simpleStudentResponse = new SimpleStudentResponse();

        simpleStudentResponse.setId(student.getId());
        simpleStudentResponse.setFullName(student.getFullName());
        simpleStudentResponse.setEmail(student.getEmail());
        simpleStudentResponse.setLoans(LoanMapper.toSimpleLoanStudentListResponse(student.getLoans()));

        return simpleStudentResponse;
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




//
//    // with loans
////    public static Student toEntity(StudentResponseDto studentResponseDto) {
////        Student student = new Student();
////
////        student.setId(studentResponseDto.getId());
////        student.setFullName(studentResponseDto.getFullName());
////        student.setEmail(studentResponseDto.getEmail());
////        student.setLoans(LoanMapper.toEntityList(studentResponseDto.getLoans()));
////
////        return student;
////
////    }
////    public static StudentResponseDto toDto(Student student) {
////        StudentResponseDto studentResponseDto = new StudentResponseDto();
////
////        studentResponseDto.setId(student.getId());
////        studentResponseDto.setFullName(student.getFullName());
////        studentResponseDto.setEmail(student.getEmail());
////        studentResponseDto.setLoans(LoanMapper.toDtoList(student.getLoans()));
////
////        return studentResponseDto;
////    }
////
//    public static Student dtoRequestToEntity(StudentRequestDto studentRequestDto) {
//        Student student = new Student();
//
//        student.setFullName(studentRequestDto.getFullName());
//        student.setEmail(studentRequestDto.getEmail());
//        student.setLoans(null);
//        //student.setLoans(LoanMapper.toEntityList(studentRequestDto.getLoans()));
//        return student;
//    }
////    public static StudentRequestDto entityToDtoRequest(Student student) {
////        StudentRequestDto studentRequestDto = new StudentRequestDto();
////
////        studentRequestDto.setFullName(student.getFullName());
////        studentRequestDto.setEmail(student.getEmail());
////        //studentRequestDto.setLoans(LoanMapper.toDtoList(student.getLoans()));
////        return studentRequestDto;
////    }
////
////    public static List < StudentResponseDto > toDtoList(List < Student > student) {
////        if (student == null) {
////            return null;
////        }
////        List < StudentResponseDto > studentResponseDtoList = new ArrayList < > ();
////
////        for (Student studentResponseDtoList1: student) {
////            studentResponseDtoList.add(toDto(studentResponseDtoList1));
////        }
////
////        return studentResponseDtoList;
////    }
////
////    public static List < Student > toEntityList(List < StudentResponseDto > studentResponseDtoList) {
////        List < Student > studentList = new ArrayList < > ();
////
////        for (StudentResponseDto studentResponseDtoList1: studentResponseDtoList) {
////            studentList.add(toEntity(studentResponseDtoList1));
////        }
////
////        return studentList;
////    }

}