package com.bibliproject.biblioteca.service;


import com.bibliproject.biblioteca.domain.dto.request.CategoryDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.CategoryResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Category;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.domain.mapper.CategoryMapper;
import com.bibliproject.biblioteca.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryResponseDto> findAll() {
        return CategoryMapper.INSTANCE.convertEntityListToListResponseDto(categoryRepository.findAll());
    }
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    public void create(Category category) {
        categoryRepository.save(category);
    }

    public CategoryResponseDto update(long id, CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.convertDtoToEntity(categoryDto);

        category.setId(id);

        categoryRepository.save(category);

        return CategoryMapper.INSTANCE.convertEntityToResponseDto(category);
    }

    public boolean delete(long id) {

        categoryRepository.deleteById(id);

        return true;
    }
}
