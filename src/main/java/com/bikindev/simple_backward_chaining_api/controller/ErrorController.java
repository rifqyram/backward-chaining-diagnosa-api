package com.bikindev.simple_backward_chaining_api.controller;

import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause().getMessage();

        if (message.contains("foreign key constraint")) {
            return new ResponseEntity<>("Tidak dapat menghapus karena ada referensi dari tabel lain.", HttpStatus.BAD_REQUEST);
        } else if (message.contains("unique constraint") || message.contains("Duplicate entry")) {
            return new ResponseEntity<>("Data tidak dapat disimpan karena duplikasi.", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("Terjadi kesalahan integritas data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
