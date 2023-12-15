package com.bikindev.simple_backward_chaining_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_indication")
public class Indication extends BaseEntity {
    @NotBlank(message = "Gejala Deskripsi tidak boleh kosong")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
