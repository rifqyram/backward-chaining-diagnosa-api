package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.dto.RuleRequest;
import com.bikindev.simple_backward_chaining_api.entity.Disease;
import com.bikindev.simple_backward_chaining_api.entity.Rule;

import java.util.List;

public interface RuleService {
    Rule createOrUpdate(RuleRequest rule);
    Rule getById(String id);
    List<Rule> getAll();
    List<Rule> getRulesForDiagnosis(Disease targetDiagnosis);
    void deleteById(String id);

}
