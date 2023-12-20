package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.entity.Symptoms;

import java.util.List;

public interface SymptomsService {
    Symptoms createOrUpdate(Symptoms symptoms);
    Symptoms getById(String id);
    List<Symptoms> getAll();
    void deleteById(String id);
}
