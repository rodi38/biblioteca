package com.bibliproject.biblioteca.domain.mapper;

import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);


//    @Mapping(target = "book.stockQuantity", source = "stockQuantity")
//    @Mapping(target = "book.title", source = "title")
//    @Mapping(target = "book.author", source = "author")
//    @Mapping(target = "book.categories", source = "categories")
//    @Mapping(target = "book.isbn", source = "isbn")
//    @Mapping(target = "book.publisher", source = "publisher")
//    @Mapping(target = "book.publishedYear", source = "publishedYear")
    @Mapping(target = "id", ignore = true)
    Book convertDtoToEntity(BookDto bookDto);

//    @Mapping(target = "stockQuantity", source = "book.stockQuantity")
//    @Mapping(target = "title", source = "book.title")
//    @Mapping(target = "author", source = "book.author")
//    @Mapping(target = "categories", source = "book.categories")
//    @Mapping(target = "isbn", source = "book.isbn")
//    @Mapping(target = "publisher", source = "book.publisher")
//    @Mapping(target = "publishedYear", source = "book.publishedYear")
    BookDto convertEntityToDto(Book book);

    List<BookDto> convertEntityListToListDto(List<Book> books);


}
