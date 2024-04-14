package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
