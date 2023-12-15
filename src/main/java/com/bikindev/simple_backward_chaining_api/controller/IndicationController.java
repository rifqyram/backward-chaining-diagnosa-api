package com.bikindev.simple_backward_chaining_api.controller;


import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import com.bikindev.simple_backward_chaining_api.entity.Indication;
import com.bikindev.simple_backward_chaining_api.service.IndicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indications")
@RequiredArgsConstructor
public class IndicationController {
    private final IndicationService indicationService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createIndication(@RequestBody Indication indication) {
        Indication newIndication = indicationService.createOrUpdate(indication);
        CommonResponse<Indication> response = CommonResponse.<Indication>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Sukses membuat data gejala")
                .data(newIndication)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getIndicationById(@PathVariable String id) {
        Indication indication = indicationService.getById(id);
        CommonResponse<Indication> response = CommonResponse.<Indication>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data gejala")
                .data(indication)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getIndications() {
        List<Indication> indications = indicationService.getAll();
        CommonResponse<List<Indication>> response = CommonResponse.<List<Indication>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data gejala")
                .data(indications)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateIndication(@RequestBody Indication aIndication) {
        Indication newIndication = indicationService.createOrUpdate(aIndication);
        CommonResponse<Indication> response = CommonResponse.<Indication>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses merubah data gejala")
                .data(newIndication)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteIndicationById(@PathVariable String id) {
        indicationService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses menghapus data gejala")
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
