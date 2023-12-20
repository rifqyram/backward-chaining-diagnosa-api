package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.dto.RuleRequest;
import com.bikindev.simple_backward_chaining_api.entity.Disease;
import com.bikindev.simple_backward_chaining_api.entity.Symptoms;
import com.bikindev.simple_backward_chaining_api.entity.Rule;
import com.bikindev.simple_backward_chaining_api.repository.RuleRepository;
import com.bikindev.simple_backward_chaining_api.service.CaseService;
import com.bikindev.simple_backward_chaining_api.service.SymptomsService;
import com.bikindev.simple_backward_chaining_api.service.RuleService;
import com.bikindev.simple_backward_chaining_api.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {
    private final RuleRepository ruleRepository;
    private final ValidationService validationService;
    private final SymptomsService symptomsService;
    private final CaseService caseService;

    @Override
    public Rule createOrUpdate(RuleRequest request) {
        validationService.validate(request);
        Disease aDisease = caseService.getById(request.getDiseaseId());

        List<Symptoms> requiredSymptomps = new ArrayList<>();
        List<Symptoms> optionalSymptomps = new ArrayList<>();
        if (request.getRequiredSymptomsIds() != null && !request.getRequiredSymptomsIds().isEmpty()) {
            for (String id : request.getRequiredSymptomsIds()) {
                Symptoms symptoms = symptomsService.getById(id);
                requiredSymptomps.add(symptoms);
            }
        }

        if (request.getOptionalSymptomsIds() != null && !request.getOptionalSymptomsIds().isEmpty()) {
            for (String id : request.getOptionalSymptomsIds()) {
                Symptoms symptoms = symptomsService.getById(id);
                optionalSymptomps.add(symptoms);
            }
        }

        Rule rule = Rule.builder()
                .id(request.getId())
                .disease(aDisease)
                .requiredSymptoms(requiredSymptomps)
                .optionalSymptoms(optionalSymptomps)
                .build();
        return ruleRepository.saveAndFlush(rule);
    }

    @Override
    public Rule getById(String id) {
        return ruleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rule tidak ditemukan"));
    }

    @Override
    public List<Rule> getAll() {
        return ruleRepository.findAll();
    }

    @Override
    public List<Rule> getRulesForDiagnosis(Disease targetDiagnosis) {
        return ruleRepository.findAllByaCase(targetDiagnosis);
    }

    @Override
    public void deleteById(String id) {
        Rule aRule = getById(id);
        ruleRepository.delete(aRule);
    }
}
