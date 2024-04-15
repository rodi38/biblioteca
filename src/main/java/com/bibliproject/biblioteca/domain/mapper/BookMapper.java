package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LoanMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book convertDtoRequestToEntity(BookRequestDto bookRequestDto);


    Book convertDtoResponseToEntity(BookResponseDto bookResponseDto);

    @Mapping(target = "hasOnStock", ignore = true)
    BookResponseDto convertEntityToResponseDto(Book book);

    BookRequestDto convertEntityToRequestDto(Book book);

    List<BookResponseDto> convertEntityListToListResponseDto(List<Book> books);

    void update(@MappingTarget Book book, BookRequestDto bookRequestDto);



}
