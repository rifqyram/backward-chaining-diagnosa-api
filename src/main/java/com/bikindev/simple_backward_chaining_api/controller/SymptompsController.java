package com.bikindev.simple_backward_chaining_api.controller;


import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import com.bikindev.simple_backward_chaining_api.entity.Symptoms;
import com.bikindev.simple_backward_chaining_api.service.SymptomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indications")
@RequiredArgsConstructor
public class SymptompsController {
    private final SymptomsService symptomsService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createSymptoms(@RequestBody Symptoms symptoms) {
        Symptoms newSymptoms = symptomsService.createOrUpdate(symptoms);
        CommonResponse<Symptoms> response = CommonResponse.<Symptoms>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Sukses membuat data gejala")
                .data(newSymptoms)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getSymptomsById(@PathVariable String id) {
        Symptoms symptoms = symptomsService.getById(id);
        CommonResponse<Symptoms> response = CommonResponse.<Symptoms>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data gejala")
                .data(symptoms)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getSymptoms() {
        List<Symptoms> symptomps = symptomsService.getAll();
        CommonResponse<List<Symptoms>> response = CommonResponse.<List<Symptoms>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data gejala")
                .data(symptomps)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateSymptoms
            (@RequestBody Symptoms aSymptoms) {
        Symptoms newSymptoms = symptomsService.createOrUpdate(aSymptoms);
        CommonResponse<Symptoms> response = CommonResponse.<Symptoms>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses merubah data gejala")
                .data(newSymptoms)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteSymptomsById(@PathVariable String id) {
        symptomsService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses menghapus data gejala")
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
