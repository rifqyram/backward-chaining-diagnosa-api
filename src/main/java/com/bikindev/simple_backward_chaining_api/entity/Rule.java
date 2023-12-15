package com.bikindev.simple_backward_chaining_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_rule")
public class Rule extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false)
    @JsonProperty("case")
    private Case aCase;

    @ManyToMany
    private List<Indication> requiredIndication;

    @ManyToMany
    private List<Indication> optionalIndication;

}
