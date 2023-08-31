package com.dominikpalichleb.trainingapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcerciseDto {
    private String name;
    private String unit;
    private int reps;
    private int value;
    private String unit2;
}
