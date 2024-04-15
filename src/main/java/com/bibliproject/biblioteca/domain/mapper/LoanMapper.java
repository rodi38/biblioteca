package com.bibliproject.biblioteca.domain.mapper;


import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {StudentMapper.class})
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "id", ignore = true)
    Loan convertDtoRequestToEntity(LoanRequestDto loanRequestDto);
    Loan convertDtoResponseToEntity(LoanResponseDto loanResponseDto);

    LoanResponseDto convertEntityToResponseDto(Loan loan);
    LoanRequestDto convertEntityToRequestDto(Loan loan);

    List<LoanResponseDto> convertEntityListToListResponseDto(List<Loan> loans);

}
