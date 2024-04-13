package com.bibliproject.biblioteca.domain.mapper;


import com.bibliproject.biblioteca.domain.dto.AuthorDto;
import com.bibliproject.biblioteca.domain.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BookMapper.class})
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    @Mapping(target = "id", ignore = true)
    Author convertDtoToEntity(AuthorDto authorDto);
    AuthorDto convertEntityToDto(Author author);


}
