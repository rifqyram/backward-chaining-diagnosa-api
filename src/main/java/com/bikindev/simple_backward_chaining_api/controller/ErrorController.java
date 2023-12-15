package com.bikindev.simple_backward_chaining_api.controller;

import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message(e.getMessage())
                .statusCode(e.getStatusCode().value())
                .build();
        return ResponseEntity.status(e.getStatusCode())
                .body(response);
    }

}
