package com.bibliproject.biblioteca.infrastructure.persistence.loan;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

    private final LoanJpaRepository loanJpaRepository;

    public LoanRepositoryImpl(LoanJpaRepository loanJpaRepository) {
        this.loanJpaRepository = loanJpaRepository;
    }

    @Override
    public Page<Loan> findAllNotDeleted(Pageable pageable) {
        return loanJpaRepository.findAllNotDeleted(pageable).map(LoanPersistenceMapper::toDomain);
    }

    @Override
    public Page<Loan> findAllNotDeletedAndMatchesSearch(String search, Pageable pageable) {
        return loanJpaRepository.findAllNotDeletedAndMatchesSearch(search, pageable).map(LoanPersistenceMapper::toDomain);
    }

    @Override
    public Page<Loan> findAllDeleted(Pageable pageable) {
        return loanJpaRepository.findAllDeleted(pageable).map(LoanPersistenceMapper::toDomain);
    }

    @Override
    public Page<Loan> findAllDeletedAndMatchesSearch(String search, Pageable pageable) {
        return loanJpaRepository.findAllDeletedAndMatchesSearch(search, pageable).map(LoanPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Loan> findByIdAndNotDeleted(Long id) {
        return loanJpaRepository.findByIdAndNotDeleted(id).map(LoanPersistenceMapper::toDomain);
    }

    @Override
    public Page<Loan> findAllNotDeletedByStudentId(Long studentId, Pageable pageable) {
        return loanJpaRepository.findAllNotDeletedByStudentId(studentId, pageable).map(LoanPersistenceMapper::toDomain);
    }

    @Override
    public List<Loan> findActiveByBookId(Long bookId) {
        return loanJpaRepository.findActiveByBookId(bookId).stream().map(LoanPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Loan> findActiveByStudentId(Long studentId) {
        return loanJpaRepository.findActiveByStudentId(studentId).stream().map(LoanPersistenceMapper::toDomain).toList();
    }

    @Override
    public Loan save(Loan loan) {
        LoanJpaEntity saved = loanJpaRepository.save(LoanPersistenceMapper.toJpaEntity(loan));
        return LoanPersistenceMapper.toDomain(saved);
    }
}
