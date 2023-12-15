package com.bikindev.simple_backward_chaining_api.security;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtClaim {
    private String userId;
    private String role;
}
