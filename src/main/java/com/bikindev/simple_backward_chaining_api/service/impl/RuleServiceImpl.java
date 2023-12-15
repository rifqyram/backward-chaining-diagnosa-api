package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.dto.RuleRequest;
import com.bikindev.simple_backward_chaining_api.entity.Case;
import com.bikindev.simple_backward_chaining_api.entity.Indication;
import com.bikindev.simple_backward_chaining_api.entity.Rule;
import com.bikindev.simple_backward_chaining_api.repository.RuleRepository;
import com.bikindev.simple_backward_chaining_api.service.CaseService;
import com.bikindev.simple_backward_chaining_api.service.IndicationService;
import com.bikindev.simple_backward_chaining_api.service.RuleService;
import com.bikindev.simple_backward_chaining_api.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final IndicationService indicationService;
    private final CaseService caseService;

    @Override
    public Rule createOrUpdate(RuleRequest request) {
        try {
            validationService.validate(request);
            Case aCase = caseService.getById(request.getCaseId());

            List<Indication> requiredIndications = new ArrayList<>();
            List<Indication> optionalIndications = new ArrayList<>();
            if (request.getRequireIndicationIds() != null && !request.getRequireIndicationIds().isEmpty()) {
                for (String id : request.getRequireIndicationIds()) {
                    Indication indication = indicationService.getById(id);
                    requiredIndications.add(indication);
                }
            }

            if (request.getOptionalIndicationIds() != null && !request.getOptionalIndicationIds().isEmpty()) {
                for (String id : request.getOptionalIndicationIds()) {
                    Indication indication = indicationService.getById(id);
                    optionalIndications.add(indication);
                }
            }

            Rule rule = Rule.builder()
                    .id(request.getId())
                    .aCase(aCase)
                    .requiredIndication(requiredIndications)
                    .optionalIndication(optionalIndications)
                    .build();
            return ruleRepository.saveAndFlush(rule);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
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
    public List<Rule> getRulesForDiagnosis(Case targetDiagnosis) {
        return ruleRepository.findAllByaCase(targetDiagnosis);
    }

    @Override
    public void deleteById(String id) {
        Rule aRule = getById(id);
        ruleRepository.delete(aRule);
    }
}
