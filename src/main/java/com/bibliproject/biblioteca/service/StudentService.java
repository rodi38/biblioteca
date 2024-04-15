package com.bibliproject.biblioteca.service;


import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import com.bibliproject.biblioteca.domain.entity.Student;
import com.bibliproject.biblioteca.domain.mapper.StudentMapper;
import com.bibliproject.biblioteca.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<StudentResponseDto> findAll() {
        return StudentMapper.INSTANCE.convertEntityListToListResponseDto(studentRepository.findAll());
    }
    public StudentResponseDto findById(long id) {
        return StudentMapper.INSTANCE.convertEntityToResponseDto(studentRepository.findById(id).get());
    }

    public StudentResponseDto create(StudentRequestDto studentRequestDto) {
        System.out.println(studentRequestDto.getEmail());
        Student student = StudentMapper.INSTANCE.convertDtoRequestToEntity(studentRequestDto);
        studentRepository.save(student);

        return StudentMapper.INSTANCE.convertEntityToResponseDto(student);
    }

    public StudentResponseDto update(long id, StudentRequestDto studentRequestDto) {
        Student student = StudentMapper.INSTANCE.convertDtoRequestToEntity(studentRequestDto);

        student.setId(id);

        studentRepository.save(student);

        return StudentMapper.INSTANCE.convertEntityToResponseDto(student);
    }

    public boolean delete(long id) {

        studentRepository.deleteById(id);

        return true;
    }

}
