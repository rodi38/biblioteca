package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.AuthorDto;
import com.bibliproject.biblioteca.domain.dto.response.AuthorResponseDto;
import com.bibliproject.biblioteca.domain.entity.Author;
import com.bibliproject.biblioteca.domain.mapper.AuthorMapper;
import com.bibliproject.biblioteca.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    public void create(Author author) {
        authorRepository.save(author);
    }

    public AuthorResponseDto update(long id, AuthorDto authorDto) {
        Author author = AuthorMapper.INSTANCE.convertDtoToEntity(authorDto);

        author.setId(id);

        authorRepository.save(author);

        return AuthorMapper.INSTANCE.convertEntityToResponseDto(author);
    }

    public boolean delete(long id) {

        authorRepository.deleteById(id);

        return true;
    }
}
