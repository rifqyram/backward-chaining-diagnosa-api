package com.bikindev.simple_backward_chaining_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Username tidak boleh kosong")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username tidak valid")
    @Size(min = 6, max = 16, message = "Username minimal harus 6 - 16 karakter")
    private String username;
    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 6, message = "Password harus lebih dari 6 karakter")
    private String password;

}
