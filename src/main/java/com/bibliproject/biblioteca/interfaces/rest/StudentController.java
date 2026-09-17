package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.student.CreateStudentUseCase;
import com.bibliproject.biblioteca.application.student.DeleteStudentUseCase;
import com.bibliproject.biblioteca.application.student.FindStudentUseCase;
import com.bibliproject.biblioteca.application.student.ListDeletedStudentsUseCase;
import com.bibliproject.biblioteca.application.student.ListStudentsUseCase;
import com.bibliproject.biblioteca.application.student.UpdateStudentUseCase;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.interfaces.dto.request.StudentRequestDto;
import com.bibliproject.biblioteca.interfaces.dto.response.CustomResponse;
import com.bibliproject.biblioteca.interfaces.mapper.StudentDtoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final ListStudentsUseCase listStudentsUseCase;
    private final ListDeletedStudentsUseCase listDeletedStudentsUseCase;
    private final FindStudentUseCase findStudentUseCase;
    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        CustomResponse response = new CustomResponse(true, "Student has been created",
                StudentDtoMapper.toSimpleResponse(createStudentUseCase.execute(StudentDtoMapper.toDomain(studentRequestDto))));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<CustomResponse> findAllNotDeleted(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all students",
                listStudentsUseCase.execute(search, pageable).map(StudentDtoMapper::toSimpleResponse));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/audit")
    public ResponseEntity<CustomResponse> findAllDeleted(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size,
                                                          @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse response = new CustomResponse(true, "Successfully get all students",
                listDeletedStudentsUseCase.execute(search, pageable).map(StudentDtoMapper::toAudityResponse));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> update(@Valid @RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully updated student",
                StudentDtoMapper.toSimpleResponse(updateStudentUseCase.execute(id, studentRequestDto.getFullName(),
                        new Email(studentRequestDto.getEmail()))));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> findById(@PathVariable Long id) {
        CustomResponse response = new CustomResponse(true, "Successfully get student",
                StudentDtoMapper.toSimpleResponse(findStudentUseCase.execute(id)));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteStudentUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
