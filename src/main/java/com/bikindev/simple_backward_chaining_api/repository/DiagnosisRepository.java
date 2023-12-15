package com.bikindev.simple_backward_chaining_api.repository;

import com.bikindev.simple_backward_chaining_api.entity.Diagnosis;
import com.bikindev.simple_backward_chaining_api.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, String> {
    List<Diagnosis> findByUserCredential(UserCredential userCredential);
}
