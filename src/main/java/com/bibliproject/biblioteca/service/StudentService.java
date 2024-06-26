package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.audity.StudentAudityResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.student.SimpleStudentResponse;
import com.bibliproject.biblioteca.domain.entity.Student;
import com.bibliproject.biblioteca.domain.mapper.StudentMapper;
import com.bibliproject.biblioteca.domain.mapper.mapstructmapper.StudentMapperMapstruct;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import com.bibliproject.biblioteca.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page <SimpleStudentResponse> findAllNotDeleted(String search, Pageable pageable) {
        Page<Student> students;
        if (search != null) {
            students  = studentRepository.findAllNotDeletedAndMatchesSearch(search, pageable);

        } else {
            students  = studentRepository.findAllNotDeleted(pageable);

        }
        return students.map(StudentMapper::toSimpleStudentResponse);
    }

    public Page <StudentAudityResponseDto> findAllDeleted(String search, Pageable pageable) {
        Page<Student> students;
        if (search != null) {
            students  = studentRepository.findAllDeletedAndMatchesSearch(search, pageable);

        } else {
            students  = studentRepository.findAllDeleted(pageable);

        }
        return students.map(StudentMapperMapstruct.INSTANCE::studentToStudentAudityResponseDto);
    }


    public SimpleStudentResponse findById(long id) {
        return StudentMapper.toSimpleStudentResponse(studentRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new StudentNotFoundException(String.format("O estudante com o id: %d não foi encontrado.  ", id))));
    }

    public SimpleStudentResponse create(StudentRequestDto studentRequestDto) {
        Student student = StudentMapper.dtoRequestToEntity(studentRequestDto);
        studentRepository.save(student);

        return StudentMapper.toSimpleStudentResponse(student);
    }

    public SimpleStudentResponse update(long id, StudentRequestDto studentRequestDto) {
        Student student = StudentMapper.simpleStudentResponseToEntity(findById(id));
        student.setFullName(studentRequestDto.getFullName());
        student.setEmail(studentRequestDto.getEmail());

        student.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student);

        return StudentMapper.toSimpleStudentResponse(student);
    }



    public void delete(long id) {
        Student student = StudentMapper.simpleStudentResponseToEntity(findById(id));
        if (student.getBorrowedBooksCount() >0){
            throw new StudentHaveDebtException("O estudante possui livros pendentes de devolução, que devem ser entregues para que o registro seja deletado.");
        }
        student.setDeleted(true);
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
    }

}