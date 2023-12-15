package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.entity.Case;
import com.bikindev.simple_backward_chaining_api.repository.CaseRepository;
import com.bikindev.simple_backward_chaining_api.service.CaseService;
import com.bikindev.simple_backward_chaining_api.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {
    private final CaseRepository caseRepository;
    private final ValidationService validationService;

    @Override
    public Case createOrUpdate(Case aCase) {
        try {
            validationService.validate(aCase);
            return caseRepository.saveAndFlush(aCase);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Case getById(String id) {
        return caseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penyakit tidak ditemukan"));
    }

    @Override
    public List<Case> getAll() {
        return caseRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Case aCase = getById(id);
        caseRepository.delete(aCase);
    }
}
