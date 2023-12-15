package com.bikindev.simple_backward_chaining_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisRequest {
    @NotBlank(message = "Penyakit Id tidak boleh kosong")
    private String caseId;
    @NotNull(message = "Gejala tidak boleh kosong")
    private List<String> indicationsIds;
}
