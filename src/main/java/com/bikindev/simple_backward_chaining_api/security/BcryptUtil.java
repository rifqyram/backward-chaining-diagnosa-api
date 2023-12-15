package com.bikindev.simple_backward_chaining_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BcryptUtil {
    private final PasswordEncoder passwordEncoder;

    public String hashPassword(String text) {
        return passwordEncoder.encode(text);
    }
}
