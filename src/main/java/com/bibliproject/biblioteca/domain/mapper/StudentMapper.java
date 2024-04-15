package com.bibliproject.biblioteca.domain.mapper;


import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import com.bibliproject.biblioteca.domain.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LoanMapper.class})
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "id", ignore = true)
    Student convertDtoRequestToEntity(StudentRequestDto studentRequestDto);

    Student convertDtoResponseToEntity(StudentResponseDto studentResponseDto);

    StudentResponseDto convertEntityToResponseDto(Student student);
    StudentRequestDto convertEntityToRequestDto(Student student);

    List<StudentResponseDto> convertEntityListToListResponseDto(List<Student> students);

}
