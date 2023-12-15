package com.bikindev.simple_backward_chaining_api.repository;

import com.bikindev.simple_backward_chaining_api.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, String> {
}
