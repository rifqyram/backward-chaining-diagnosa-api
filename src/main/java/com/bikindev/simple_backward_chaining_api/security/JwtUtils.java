package com.bikindev.simple_backward_chaining_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bikindev.simple_backward_chaining_api.entity.UserCredential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Service
@Slf4j
public class JwtUtils {
    @Value("${app.backward-chaining.jwt-secret}")
    private String jwtSecret;
    @Value("${app.backward-chaining.app-name}")
    private String appName;
    @Value("${app.backward-chaining.jwtExpirationInSecond}")
    private long jwtExpirationInSecond;

    public String generateToken(UserCredential user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(user.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpirationInSecond))
                    .withIssuedAt(Instant.now())
                    .withClaim("role", user.getRole().name())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            log.error("error while creating jwt token: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        } catch (JWTVerificationException e) {
            log.error("invalid verification JWT: {}", e.getMessage());
            return false;
        }
    }

    public JwtClaim getUserInfoByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return JwtClaim.builder()
                    .userId(decodedJWT.getSubject())
                    .role(decodedJWT.getClaim("role").asString())
                    .build();
        } catch (JWTVerificationException e) {
            log.error("invalid verification JWT: {}", e.getMessage());
            return null;
        }
    }




}
