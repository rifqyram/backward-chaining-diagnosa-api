package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.entity.Symptoms;
import com.bikindev.simple_backward_chaining_api.repository.IndicationRepository;
import com.bikindev.simple_backward_chaining_api.service.SymptomsService;
import com.bikindev.simple_backward_chaining_api.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SymptomsServiceImpl implements SymptomsService {
    private final IndicationRepository indicationRepository;
    private final ValidationService validationService;

    @Override
    public Symptoms createOrUpdate(Symptoms symptoms) {
        validationService.validate(symptoms);
        return indicationRepository.saveAndFlush(symptoms);
    }

    @Override
    public Symptoms getById(String id) {
        return indicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gejala tidak ditemukan"));
    }

    @Override
    public List<Symptoms> getAll() {
        return indicationRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Symptoms aSymptoms = getById(id);
        indicationRepository.delete(aSymptoms);
    }
}
