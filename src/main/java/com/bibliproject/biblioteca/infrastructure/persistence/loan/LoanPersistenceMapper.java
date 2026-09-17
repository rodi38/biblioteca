package com.bibliproject.biblioteca.infrastructure.persistence.loan;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.infrastructure.persistence.book.BookPersistenceMapper;
import com.bibliproject.biblioteca.infrastructure.persistence.student.StudentPersistenceMapper;

public final class LoanPersistenceMapper {

    private LoanPersistenceMapper() {
    }

    public static Loan toDomain(LoanJpaEntity entity) {
        Loan loan = toDomainWithoutStudent(entity);
        loan.setStudent(StudentPersistenceMapper.toDomain(entity.getStudent()));
        return loan;
    }

    public static Loan toDomainWithoutStudent(LoanJpaEntity entity) {
        Loan loan = new Loan();
        loan.setId(entity.getId());
        loan.setBook(BookPersistenceMapper.toDomain(entity.getBook()));
        loan.setLoanDate(entity.getLoanDate());
        loan.setReturnDate(entity.getReturnDate());
        loan.setLimitDate(entity.getLimitDate());
        loan.setDeletedAt(entity.getDeletedAt());
        loan.setDeleted(entity.isDeleted());
        return loan;
    }

    public static LoanJpaEntity toJpaEntity(Loan loan) {
        LoanJpaEntity entity = new LoanJpaEntity();
        entity.setId(loan.getId());
        entity.setBook(BookPersistenceMapper.toJpaEntity(loan.getBook()));
        entity.setStudent(StudentPersistenceMapper.toJpaEntity(loan.getStudent()));
        entity.setLoanDate(loan.getLoanDate());
        entity.setReturnDate(loan.getReturnDate());
        entity.setLimitDate(loan.getLimitDate());
        entity.setDeletedAt(loan.getDeletedAt());
        entity.setDeleted(loan.isDeleted());
        return entity;
    }
}
