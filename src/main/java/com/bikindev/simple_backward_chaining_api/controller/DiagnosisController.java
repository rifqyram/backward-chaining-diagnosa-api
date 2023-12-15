package com.bikindev.simple_backward_chaining_api.controller;

import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import com.bikindev.simple_backward_chaining_api.dto.DiagnosisRequest;
import com.bikindev.simple_backward_chaining_api.dto.DiagnosisResponse;
import com.bikindev.simple_backward_chaining_api.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService diagnosisService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createDiagnose(@RequestBody DiagnosisRequest request) {
        DiagnosisResponse diagnosis = diagnosisService.create(request);
        CommonResponse<DiagnosisResponse> response = CommonResponse.<DiagnosisResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Berhasil membuat diagnosa baru")
                .data(diagnosis)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getDiagnoseById(@PathVariable String id) {
        DiagnosisResponse diagnosis = diagnosisService.getById(id);
        CommonResponse<DiagnosisResponse> response = CommonResponse.<DiagnosisResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Berhasil mengambil data diagnosa")
                .data(diagnosis)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllDiagnose() {
        List<DiagnosisResponse> diagnoses = diagnosisService.getAll();
        CommonResponse<List<DiagnosisResponse>> response = CommonResponse.<List<DiagnosisResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Berhasil mengambil data diagnosa")
                .data(diagnoses)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(
            value = "/me",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getDiagnoseByCurrentUser() {
        List<DiagnosisResponse> diagnoses = diagnosisService.getAllByUser();
        CommonResponse<List<DiagnosisResponse>> response = CommonResponse.<List<DiagnosisResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Berhasil mengambil data diagnosa")
                .data(diagnoses)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
