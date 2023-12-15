package com.bikindev.simple_backward_chaining_api.controller;


import com.bikindev.simple_backward_chaining_api.dto.CommonResponse;
import com.bikindev.simple_backward_chaining_api.dto.RuleRequest;
import com.bikindev.simple_backward_chaining_api.entity.Rule;
import com.bikindev.simple_backward_chaining_api.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class RuleController {
    private final RuleService ruleService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createRule(@RequestBody RuleRequest request) {
        Rule newRule = ruleService.createOrUpdate(request);
        CommonResponse<Rule> response = CommonResponse.<Rule>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Sukses membuat data rule")
                .data(newRule)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getRuleById(@PathVariable String id) {
        Rule aRule = ruleService.getById(id);
        CommonResponse<Rule> response = CommonResponse.<Rule>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data rule")
                .data(aRule)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getRules() {
        List<Rule> rules = ruleService.getAll();
        CommonResponse<List<Rule>> response = CommonResponse.<List<Rule>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses mengambil data rule")
                .data(rules)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateRule(@RequestBody RuleRequest request) {
        Rule newRule = ruleService.createOrUpdate(request);
        CommonResponse<Rule> response = CommonResponse.<Rule>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses merubah data rule")
                .data(newRule)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteRuleById(@PathVariable String id) {
        ruleService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Sukses menghapus data rule")
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
