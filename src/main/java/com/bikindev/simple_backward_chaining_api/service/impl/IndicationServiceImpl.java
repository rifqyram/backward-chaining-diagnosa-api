package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.entity.Indication;
import com.bikindev.simple_backward_chaining_api.repository.IndicationRepository;
import com.bikindev.simple_backward_chaining_api.service.IndicationService;
import com.bikindev.simple_backward_chaining_api.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndicationServiceImpl implements IndicationService {
    private final IndicationRepository indicationRepository;
    private final ValidationService validationService;

    @Override
    public Indication createOrUpdate(Indication indication) {
        try {
            validationService.validate(indication);
            return indicationRepository.saveAndFlush(indication);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Indication getById(String id) {
        return indicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gejala tidak ditemukan"));
    }

    @Override
    public List<Indication> getAll() {
        return indicationRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Indication aIndication = getById(id);
        indicationRepository.delete(aIndication);
    }
}
