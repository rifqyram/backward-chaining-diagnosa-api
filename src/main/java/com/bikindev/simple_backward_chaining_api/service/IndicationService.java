package com.bikindev.simple_backward_chaining_api.service;

import com.bikindev.simple_backward_chaining_api.entity.Indication;

import java.util.List;

public interface IndicationService {
    Indication createOrUpdate(Indication indication);
    Indication getById(String id);
    List<Indication> getAll();
    void deleteById(String id);
}
