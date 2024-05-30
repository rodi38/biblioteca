package com.bibliproject.biblioteca.domain.mapper.mapstructmapper;


import com.bibliproject.biblioteca.domain.dto.response.audity.BookAudityResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapperMapstruct {


    BookMapperMapstruct INSTANCE = Mappers.getMapper(BookMapperMapstruct.class);


    BookAudityResponseDto bookToBookAudityResponseDto(Book book);

    List<BookAudityResponseDto> booksToBookAudityResponseList(List<Book> books);



}
