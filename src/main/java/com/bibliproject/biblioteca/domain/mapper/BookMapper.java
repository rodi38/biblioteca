package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.domain.dto.request.BookForRequestPlus;
import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    //    @Mapping(target = "id", ignore = true)
//    Book convertDtoToEntity(BookDto bookDto);
    @Mapping(target = "id", ignore = true)
    Book convertDtoRequestToEntity(BookRequestDto bookRequestDto);

    Book convertDtoResponsetToEntity(BookResponseDto bookRequestDto);

    BookResponseDto convertEntityToDto(Book book);

    List<BookResponseDto> convertEntityListToListDto(List<Book> books);

    void update(@MappingTarget Book book, BookForRequestPlus bookRequestDto);


}
