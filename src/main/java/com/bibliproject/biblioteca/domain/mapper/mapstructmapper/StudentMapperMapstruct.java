package com.bibliproject.biblioteca.domain.mapper.mapstructmapper;

import com.bibliproject.biblioteca.domain.dto.response.audity.LoanAudityResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.audity.StudentAudityResponseDto;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapperMapstruct {

    StudentMapperMapstruct INSTANCE = Mappers.getMapper(StudentMapperMapstruct.class);


    StudentAudityResponseDto studentToStudentAudityResponseDto(Student student);

    @Mapping(target = "student", ignore = true)
    List<LoanAudityResponseDto> loansToLoansAudityResponseDtoList(List<Loan> loans);
}
