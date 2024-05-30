package com.bibliproject.biblioteca.domain.mapper.mapstructmapper;

import com.bibliproject.biblioteca.domain.dto.response.audity.BookAudityResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.audity.LoanAudityResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.audity.StudentAudityResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapperMapstruct {
    LoanMapperMapstruct INSTANCE = Mappers.getMapper(LoanMapperMapstruct.class);


    LoanAudityResponseDto loanToAudityResponseDto(Loan loan);

    BookAudityResponseDto bookToAudityResponseDto(Book book);

    @Mapping(target = "loans", ignore = true)
    StudentAudityResponseDto studentToAudityResponseDto(Student student);


}
