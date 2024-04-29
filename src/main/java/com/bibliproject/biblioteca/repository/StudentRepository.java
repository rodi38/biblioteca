package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT l FROM Student l WHERE l.isDeleted = false")
    List<Loan> findAllNotDeleted();

}
