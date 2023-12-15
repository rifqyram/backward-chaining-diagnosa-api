package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.dto.AuthRequest;
import com.bikindev.simple_backward_chaining_api.dto.LoginResponse;
import com.bikindev.simple_backward_chaining_api.dto.UserResponse;

public interface AuthService {
    UserResponse register(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
