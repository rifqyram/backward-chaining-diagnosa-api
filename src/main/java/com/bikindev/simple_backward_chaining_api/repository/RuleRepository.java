package com.bikindev.simple_backward_chaining_api.repository;

import com.bikindev.simple_backward_chaining_api.entity.Case;
import com.bikindev.simple_backward_chaining_api.entity.Indication;
import com.bikindev.simple_backward_chaining_api.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleRepository extends JpaRepository<Rule, String> {
    List<Rule> findAllByaCase(Case aCase);
}
