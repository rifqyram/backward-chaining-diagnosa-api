package com.bikindev.simple_backward_chaining_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_disease")
public class Disease extends BaseEntity {
    @NotBlank(message = "Kode Penyakit tidak boleh kosong")
    @Column(unique = true, nullable = false)
    private String diseaseCode;
    @NotBlank(message = "Nama Penyakit tidak boleh kosong")
    @Column(unique = true, nullable = false)
    private String name;
    @NotBlank(message = "Penyebab tidak boleh kosong")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String cause;
    @NotBlank(message = "Solusi tidak boleh kosong")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String solution;
}
