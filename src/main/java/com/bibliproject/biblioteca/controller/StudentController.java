package com.bibliproject.biblioteca.controller;


import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.StudentResponseDto;
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
    public ResponseEntity<StudentResponseDto> create(@RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto response = studentService.create(studentRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAll() {
        List<StudentResponseDto> students = studentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> update(@RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id) {

        StudentResponseDto studentResponseDto = studentService.update(id, studentRequestDto);

        return ResponseEntity.ok(studentResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable Long id) {
        StudentResponseDto studentResponseDto = studentService.findById(id);
        return ResponseEntity.ok(studentResponseDto);
    }

}
