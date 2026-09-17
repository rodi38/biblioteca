package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.auth.LoginUseCase;
import com.bibliproject.biblioteca.application.auth.RegisterUseCase;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.interfaces.dto.request.LoginRequestDto;
import com.bibliproject.biblioteca.interfaces.dto.request.RegisterRequestDto;
import com.bibliproject.biblioteca.interfaces.dto.response.CustomResponse;
import com.bibliproject.biblioteca.interfaces.dto.response.LoginResponseDto;
import com.bibliproject.biblioteca.interfaces.mapper.StudentDtoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody RegisterRequestDto requestDto) {
        CustomResponse response = new CustomResponse(true, "Cadastro realizado com sucesso.",
                StudentDtoMapper.toSimpleResponse(registerUseCase.execute(
                        requestDto.getFullName(), new Email(requestDto.getEmail()), requestDto.getPassword())));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@Valid @RequestBody LoginRequestDto requestDto) {
        String token = loginUseCase.execute(requestDto.getEmail(), requestDto.getPassword());
        CustomResponse response = new CustomResponse(true, "Login realizado com sucesso.", new LoginResponseDto(token));
        return ResponseEntity.ok(response);
    }
}
