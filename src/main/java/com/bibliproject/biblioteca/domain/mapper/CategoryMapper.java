package com.bibliproject.biblioteca.domain.mapper;


import com.bibliproject.biblioteca.domain.dto.request.CategoryDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.dto.response.CategoryResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BookMapper.class})
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //@Mapping(target = "book", ignore = true)
    Category convertDtoToEntity(CategoryDto categoryDto);
    CategoryResponseDto convertEntityToResponseDto(Category category);

    List<CategoryResponseDto> convertEntityListToListResponseDto(List<Category> categories);

}
