package com.bikindev.simple_backward_chaining_api.controller;

import com.bikindev.simple_backward_chaining_api.dto.AuthRequest;
import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import com.bikindev.simple_backward_chaining_api.dto.LoginResponse;
import com.bikindev.simple_backward_chaining_api.dto.UserResponse;
import com.bikindev.simple_backward_chaining_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        UserResponse registerResponse = authService.register(request);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("berhasil daftar user baru")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        LoginResponse login = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("berhasil login")
                .statusCode(HttpStatus.OK.value())
                .data(login)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
