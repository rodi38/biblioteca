package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.LoanResponseDto;
import com.bibliproject.biblioteca.domain.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);
    Loan convertDtoToEntity(LoanRequestDto studentRequestDto);
    LoanResponseDto convertEntityToResponseDto(Loan student);

    List<LoanResponseDto> convertEntityListToListResponseDto(List<Loan> students);
}
