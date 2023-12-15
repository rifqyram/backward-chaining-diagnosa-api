package com.bikindev.simple_backward_chaining_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RuleRequest {
    private String id;
    @NotBlank(message = "Id Penyakit tidak boleh kosong")
    private String caseId;
    private List<String> requireIndicationIds;
    private List<String> optionalIndicationIds;
}
