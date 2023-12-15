package com.bikindev.simple_backward_chaining_api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private String username;
    private String role;
}
