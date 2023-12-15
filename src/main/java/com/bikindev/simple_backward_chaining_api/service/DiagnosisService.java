package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.dto.DiagnosisRequest;
import com.bikindev.simple_backward_chaining_api.dto.DiagnosisResponse;

import java.util.List;

public interface DiagnosisService {
    DiagnosisResponse create(DiagnosisRequest diagnosis);
    DiagnosisResponse getById(String id);
    List<DiagnosisResponse> getAll();
    List<DiagnosisResponse> getAllByUser();
}
