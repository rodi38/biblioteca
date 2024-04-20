package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleStudentResponse;
import com.bibliproject.biblioteca.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity < SimpleStudentResponse > create(@RequestBody StudentRequestDto studentRequestDto) {
        SimpleStudentResponse response = studentService.create(studentRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity < List <SimpleStudentResponse>> findAll() {
        List < SimpleStudentResponse > students = studentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity < SimpleStudentResponse > update(@RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id) {

        SimpleStudentResponse studentResponseDto = studentService.update(id, studentRequestDto);

        return ResponseEntity.ok(studentResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity < SimpleStudentResponse > findById(@PathVariable Long id) {
        SimpleStudentResponse studentResponseDto = studentService.findById(id);
        return ResponseEntity.ok(studentResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Boolean > delete(@PathVariable Long id) {
        Boolean isDeleted = studentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }

}