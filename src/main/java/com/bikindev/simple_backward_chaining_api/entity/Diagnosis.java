package com.bikindev.simple_backward_chaining_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_diagnosis")
public class Diagnosis extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case aCase;
    private Double percentage;
    @ManyToMany
    private List<Indication> indications;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserCredential userCredential;
    @Temporal(TemporalType.TIMESTAMP)
    private Date diagnoseAt;
}
