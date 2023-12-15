package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.entity.Case;

import java.util.List;

public interface CaseService {
    Case createOrUpdate(Case aCase);
    Case getById(String id);
    List<Case> getAll();
    void deleteById(String id);
}
