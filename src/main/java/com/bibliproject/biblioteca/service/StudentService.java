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
        return StudentMapper.toDtoList(studentRepository.findAll());
    }
    public StudentResponseDto findById(long id) {
        return StudentMapper.toDto(studentRepository.findById(id).get());
    }

//    public Student findByIdEntity(long id) {
//        return studentRepository.findById(id).get();
//    }

    public StudentResponseDto create(StudentRequestDto studentRequestDto) {
        System.out.println(studentRequestDto.getEmail());
        Student student = StudentMapper.dtoRequestToEntity(studentRequestDto);
        System.out.println(student.getId() + " " + student.getEmail() + " " + student.getLoans());
        studentRepository.save(student);

        return StudentMapper.toDto(student);
    }

    public StudentResponseDto update(long id, StudentRequestDto studentRequestDto) {
        Student student = StudentMapper.dtoRequestToEntity(studentRequestDto);

        student.setId(id);

        studentRepository.save(student);

        return StudentMapper.toDto(student);
    }

    public boolean delete(long id) {

        studentRepository.deleteById(id);

        return true;
    }

}
