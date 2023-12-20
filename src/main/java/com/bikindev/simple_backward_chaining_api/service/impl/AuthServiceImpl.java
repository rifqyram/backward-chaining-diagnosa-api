package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.constant.Role;
import com.bikindev.simple_backward_chaining_api.dto.AuthRequest;
import com.bikindev.simple_backward_chaining_api.dto.LoginResponse;
import com.bikindev.simple_backward_chaining_api.dto.UserResponse;
import com.bikindev.simple_backward_chaining_api.entity.UserCredential;
import com.bikindev.simple_backward_chaining_api.repository.UserRepository;
import com.bikindev.simple_backward_chaining_api.security.BcryptUtil;
import com.bikindev.simple_backward_chaining_api.security.JwtUtils;
import com.bikindev.simple_backward_chaining_api.service.AuthService;
import com.bikindev.simple_backward_chaining_api.service.ValidationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BcryptUtil bcryptUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ValidationService validationService;

    @Value("${app.backward-chaining.username}")
    String superUserUsername;
    @Value("${app.backward-chaining.password}")
    String superUserPassword;


    @PostConstruct
    public void init() {
        Optional<UserCredential> credential = userRepository.findByUsername(superUserUsername);
        if (credential.isPresent()) return;
        userRepository.save(UserCredential.builder()
                .username(superUserUsername)
                .password(bcryptUtil.hashPassword(superUserPassword))
                .role(Role.ROLE_SUPER_ADMIN)
                .build());
    }

    @Override
    public UserResponse register(AuthRequest request) {
        validationService.validate(request);
        UserCredential userCredential = UserCredential.builder()
                .username(request.getUsername())
                .password(bcryptUtil.hashPassword(request.getPassword()))
                .role(Role.ROLE_USER).build();
        userRepository.saveAndFlush(userCredential);
        return UserResponse.builder()
                .username(userCredential.getUsername())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        validationService.validate(request);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserCredential user = (UserCredential) authenticate.getPrincipal();

        String token = jwtUtils.generateToken(user);

        return LoginResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().getName())
                .token(token)
                .build();
    }
}
