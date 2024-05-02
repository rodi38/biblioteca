package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT l FROM Student l WHERE l.isDeleted = false")
    List<Student> findAllNotDeleted();

    @Query("SELECT l FROM Student l WHERE l.isDeleted = true")
    List<Student> findAllDeleted();

    @Query("SELECT l FROM Student l WHERE l.id = :id AND l.isDeleted = false")
    Optional<Student> findByIdAndNotDeleted(@Param("id") Long id);

}
