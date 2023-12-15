package com.bikindev.simple_backward_chaining_api.service.impl;

import com.bikindev.simple_backward_chaining_api.dto.DiagnosisRequest;
import com.bikindev.simple_backward_chaining_api.dto.DiagnosisResponse;
import com.bikindev.simple_backward_chaining_api.dto.UserResponse;
import com.bikindev.simple_backward_chaining_api.entity.*;
import com.bikindev.simple_backward_chaining_api.repository.DiagnosisRepository;
import com.bikindev.simple_backward_chaining_api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final ValidationService validationService;
    private final RuleService ruleService;
    private final CaseService caseService;
    private final IndicationService indicationService;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DiagnosisResponse create(DiagnosisRequest request) {
        validationService.validate(request);
        UserCredential currentUser = userService.getCurrentUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
        Case targetCase = caseService.getById(request.getCaseId());
        List<Indication> indications = request.getIndicationsIds().stream()
                .map(indicationService::getById)
                .collect(Collectors.toList());

        List<Rule> rules = ruleService.getRulesForDiagnosis(targetCase);
        int totalRules = rules.size();
        int matchedRules = 0;

        for (Rule rule : rules) {
            if (isRuleFulfilled(rule, indications)) {
                matchedRules++;
            }
        }

        double matchPercentage = totalRules > 0 ? (double) matchedRules / totalRules * 100 : 0;

        Diagnosis diagnosis = Diagnosis.builder()
                .userCredential(currentUser)
                .aCase(targetCase)
                .percentage(matchPercentage)
                .diagnoseAt(new Date())
                .indications(indications)
                .build();

        diagnosisRepository.saveAndFlush(diagnosis);

        return toDiagnosisResponse(diagnosis);
    }

    @Override
    public DiagnosisResponse getById(String id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Diagnosis tidak ditemukan"));
        return toDiagnosisResponse(diagnosis);
    }

    @Override
    public List<DiagnosisResponse> getAll() {
        List<Diagnosis> diagnoses = diagnosisRepository.findAll();
        return diagnoses.stream().map(this::toDiagnosisResponse).toList();
    }

    @Override
    public List<DiagnosisResponse> getAllByUser() {
        UserCredential currentUser = userService.getCurrentUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
        List<Diagnosis> diagnoses = diagnosisRepository.findByUserCredential(currentUser);
        return diagnoses.stream().map(this::toDiagnosisResponse).toList();
    }


    private DiagnosisResponse toDiagnosisResponse(Diagnosis diagnosis) {
        return DiagnosisResponse.builder()
                .id(diagnosis.getId())
                .user(UserResponse.builder()
                        .username(diagnosis.getUserCredential().getUsername())
                        .role(diagnosis.getUserCredential().getRole().getName())
                        .build())
                .aCase(diagnosis.getACase())
                .percentage(diagnosis.getPercentage())
                .indications(diagnosis.getIndications())
                .diagnoseAt(diagnosis.getDiagnoseAt())
                .build();
    }

    private boolean isRuleFulfilled(Rule rule, List<Indication> indications) {
        if (rule.getRequiredIndication().isEmpty() || new HashSet<>(indications).containsAll(rule.getRequiredIndication())) {
            return true;
        }

        boolean optionalIndication = false;
        if (!rule.getOptionalIndication().isEmpty()) {
            for (Indication indication : rule.getOptionalIndication()) {
                if (indications.contains(indication)) {
                    optionalIndication = true;
                    break;
                }
            }
        }
        return optionalIndication;
    }
}
