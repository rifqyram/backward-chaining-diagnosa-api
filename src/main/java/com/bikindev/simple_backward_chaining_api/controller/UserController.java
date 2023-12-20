package com.bikindev.simple_backward_chaining_api.controller;

import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import com.bikindev.simple_backward_chaining_api.dto.UserResponse;
import com.bikindev.simple_backward_chaining_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        UserResponse userResponse = userService.getCurrentUserResponse();
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data user")
                .data(userResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
