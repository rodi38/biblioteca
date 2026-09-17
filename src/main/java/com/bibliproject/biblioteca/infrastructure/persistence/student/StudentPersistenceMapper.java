package com.bibliproject.biblioteca.infrastructure.persistence.student;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.infrastructure.persistence.loan.LoanJpaEntity;
import com.bibliproject.biblioteca.infrastructure.persistence.loan.LoanPersistenceMapper;

import java.util.List;

public final class StudentPersistenceMapper {

    private StudentPersistenceMapper() {
    }

    public static Student toDomain(StudentJpaEntity entity) {
        List<LoanJpaEntity> loanEntities = entity.getLoans();
        List<Loan> loans = loanEntities == null ? null
                : loanEntities.stream().map(LoanPersistenceMapper::toDomainWithoutStudent).toList();

        return new Student(
                entity.getId(),
                entity.getFullName(),
                new Email(entity.getEmail()),
                loans,
                entity.getBorrowedBooksCount(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt(),
                entity.isDeleted()
        );
    }

    public static StudentJpaEntity toJpaEntity(Student student) {
        StudentJpaEntity entity = new StudentJpaEntity();
        entity.setId(student.getId());
        entity.setFullName(student.getFullName());
        entity.setEmail(student.getEmail().value());
        entity.setBorrowedBooksCount(student.getBorrowedBooksCount());
        entity.setCreatedAt(student.getCreatedAt());
        entity.setUpdatedAt(student.getUpdatedAt());
        entity.setDeletedAt(student.getDeletedAt());
        entity.setDeleted(student.isDeleted());
        return entity;
    }
}
