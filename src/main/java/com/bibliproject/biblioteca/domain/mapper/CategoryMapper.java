package com.bibliproject.biblioteca.domain.mapper;


import com.bibliproject.biblioteca.domain.dto.CategoryDto;
import com.bibliproject.biblioteca.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BookMapper.class})
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", ignore = true)
    Category convertDtoToEntity(CategoryDto categoryDto);
    CategoryDto convertEntityToDto(Category category);


}
