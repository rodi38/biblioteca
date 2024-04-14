package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.LoanResponseDto;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.mapper.LoanMapper;
import com.bibliproject.biblioteca.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    public List<LoanResponseDto> findAll() {
        return LoanMapper.INSTANCE.convertEntityListToListResponseDto(loanRepository.findAll());
    }
    public LoanResponseDto findById(long id) {
        return LoanMapper.INSTANCE.convertEntityToResponseDto(loanRepository.findById(id).get());
    }

    public LoanResponseDto create(LoanRequestDto loanRequestDto) {
        Loan loan = LoanMapper.INSTANCE.convertDtoToEntity(loanRequestDto);
        loanRepository.save(loan);

        return LoanMapper.INSTANCE.convertEntityToResponseDto(loan);
    }

    public LoanResponseDto update(long id, LoanRequestDto loanRequestDto) {
        Loan loan = LoanMapper.INSTANCE.convertDtoToEntity(loanRequestDto);

        loan.setId(id);

        loanRepository.save(loan);

        return LoanMapper.INSTANCE.convertEntityToResponseDto(loan);
    }

    public boolean delete(long id) {

        loanRepository.deleteById(id);

        return true;
    }
}
