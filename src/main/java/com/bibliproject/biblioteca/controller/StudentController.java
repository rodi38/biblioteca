package com.bibliproject.biblioteca.controller;

import com.bibliproject.biblioteca.domain.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CustomResponse;
import com.bibliproject.biblioteca.service.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity < CustomResponse > create(@RequestBody StudentRequestDto studentRequestDto) {
        CustomResponse response = new CustomResponse(true, "Student has been created",studentService.create(studentRequestDto));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity <CustomResponse> findAllNotDeleted(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all students",studentService.findAllNotDeleted(search, pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/audit")
    public ResponseEntity <CustomResponse> findAllDeleted(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all students",studentService.findAllDeleted(search, pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity < CustomResponse > update(@RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully updated student", studentService.update(id, studentRequestDto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity < CustomResponse > findById(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully get student", studentService.findById(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Void > delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}