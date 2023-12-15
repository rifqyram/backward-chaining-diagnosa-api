package com.bikindev.simple_backward_chaining_api.dto;

import com.bikindev.simple_backward_chaining_api.entity.Case;
import com.bikindev.simple_backward_chaining_api.entity.Indication;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisResponse {
    private String id;
    private UserResponse user;
    private List<Indication> indications;
    private Case aCase;
    private Double percentage;
    private Date diagnoseAt;

    @JsonProperty("case")
    public Case getaCase() {
        return aCase;
    }
}
