package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
