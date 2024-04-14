package com.bibliproject.biblioteca.domain.mapper;


import com.bibliproject.biblioteca.domain.dto.request.CategoryDto;
import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CategoryResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import com.bibliproject.biblioteca.domain.entity.Category;
import com.bibliproject.biblioteca.domain.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student convertDtoToEntity(StudentRequestDto studentRequestDto);
    StudentResponseDto convertEntityToResponseDto(Student student);

    List<StudentResponseDto> convertEntityListToListResponseDto(List<Student> students);
}
