package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleStudentResponse;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Student;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.domain.mapper.StudentMapper;
import com.bibliproject.biblioteca.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List <SimpleStudentResponse> findAll() {
        List<Student> students = studentRepository.findAll();
        return StudentMapper.toSimpleStudentResponseList(students);
    }
    public SimpleStudentResponse findById(long id) {
        return StudentMapper.toSimpleStudentResponse(studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id)));
    }

    public SimpleStudentResponse create(StudentRequestDto studentRequestDto) {
        System.out.println(studentRequestDto.getEmail());
        if (studentRequestDto == null) {
            throw new NullPointerException("livro nulo.");
        }
        Student student = StudentMapper.dtoRequestToEntity(studentRequestDto);
        //System.out.println(student.getId() + " " + student.getEmail() + " " + student.getLoans());
        studentRepository.saveAndFlush(student);

        return StudentMapper.toSimpleStudentResponse(student);
    }

    public SimpleStudentResponse update(long id, StudentRequestDto studentRequestDto) {
        Student student = StudentMapper.simpleStudentResponseToEntity(findById(id));

        student.setFullName(studentRequestDto.getFullName());
        student.setEmail(studentRequestDto.getEmail());
        studentRepository.save(student);

        return StudentMapper.toSimpleStudentResponse(student);
    }



    public boolean delete(long id) {
        Student student = StudentMapper.simpleStudentResponseToEntity(findById(id));
        studentRepository.deleteById(student.getId());
        return true;
    }

}