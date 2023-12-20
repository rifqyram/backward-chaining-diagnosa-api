package com.bikindev.simple_backward_chaining_api.controller;


import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import com.bikindev.simple_backward_chaining_api.entity.Disease;
import com.bikindev.simple_backward_chaining_api.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class DiseaseController {
    private final CaseService caseService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createCase(@RequestBody Disease disease) {
        Disease newDisease = caseService.createOrUpdate(disease);
        CommonResponse<Disease> response = CommonResponse.<Disease>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Sukses membuat data penyakit")
                .data(newDisease)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCaseById(@PathVariable String id) {
        Disease disease = caseService.getById(id);
        CommonResponse<Disease> response = CommonResponse.<Disease>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data penyakit")
                .data(disease)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCases() {
        List<Disease> diseases = caseService.getAll();
        CommonResponse<List<Disease>> response = CommonResponse.<List<Disease>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data penyakit")
                .data(diseases)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateCase(@RequestBody Disease disease) {
        Disease newDisease = caseService.createOrUpdate(disease);
        CommonResponse<Disease> response = CommonResponse.<Disease>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses merubah data penyakit")
                .data(newDisease)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteCaseById(@PathVariable String id) {
        caseService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses menghapus data penyakit")
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
