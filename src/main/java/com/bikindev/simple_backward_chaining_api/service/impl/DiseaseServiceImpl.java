package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.entity.Disease;
import com.bikindev.simple_backward_chaining_api.repository.DiseaseRepository;
import com.bikindev.simple_backward_chaining_api.service.CaseService;
import com.bikindev.simple_backward_chaining_api.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseServiceImpl implements CaseService {
    private final DiseaseRepository diseaseRepository;
    private final ValidationService validationService;

    @Override
    public Disease createOrUpdate(Disease aDisease) {
        validationService.validate(aDisease);
        return diseaseRepository.saveAndFlush(aDisease);
    }

    @Override
    public Disease getById(String id) {
        return diseaseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penyakit tidak ditemukan"));
    }

    @Override
    public List<Disease> getAll() {
        return diseaseRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Disease aDisease = getById(id);
        diseaseRepository.delete(aDisease);
    }
}
