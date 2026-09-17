package com.bibliproject.biblioteca.domain.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentRepository {

    Page<Student> findAllNotDeleted(Pageable pageable);

    Page<Student> findAllNotDeletedAndMatchesSearch(String search, Pageable pageable);

    Page<Student> findAllDeleted(Pageable pageable);

    Page<Student> findAllDeletedAndMatchesSearch(String search, Pageable pageable);

    Optional<Student> findByIdAndNotDeleted(Long id);

    Student save(Student student);
}
