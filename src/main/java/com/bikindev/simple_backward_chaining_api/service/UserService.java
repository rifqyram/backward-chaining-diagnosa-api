package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.dto.UserResponse;
import com.bikindev.simple_backward_chaining_api.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<UserCredential> getCurrentUser();
    UserResponse getCurrentUserResponse();
    UserCredential loadUserByUserId(String userId);
}
