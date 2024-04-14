package com.bibliproject.biblioteca.domain.mapper;


import com.bibliproject.biblioteca.domain.dto.request.AuthorDto;
import com.bibliproject.biblioteca.domain.dto.response.AuthorResponseDto;
import com.bibliproject.biblioteca.domain.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BookMapper.class})
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    //@Mapping(target = "book", ignore = true)
    Author convertDtoToEntity(AuthorDto authorDto);
    AuthorResponseDto convertEntityToResponseDto(Author author);


}
