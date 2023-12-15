package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.dto.RuleRequest;
import com.bikindev.simple_backward_chaining_api.entity.Case;
import com.bikindev.simple_backward_chaining_api.entity.Indication;
import com.bikindev.simple_backward_chaining_api.entity.Rule;

import java.util.List;

public interface RuleService {
    Rule createOrUpdate(RuleRequest rule);
    Rule getById(String id);
    List<Rule> getAll();
    List<Rule> getRulesForDiagnosis(Case targetDiagnosis);
    void deleteById(String id);

}
