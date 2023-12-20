package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.entity.Disease;

import java.util.List;

public interface CaseService {
    Disease createOrUpdate(Disease aDisease);
    Disease getById(String id);
    List<Disease> getAll();
    void deleteById(String id);
}
