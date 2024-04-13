package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
