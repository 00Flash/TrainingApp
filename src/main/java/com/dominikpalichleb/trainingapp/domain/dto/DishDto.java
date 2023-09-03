package com.dominikpalichleb.trainingapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    private int kcal;
    private int fat;
    private int protein;
    private int carbon;
    private String name;
    private int amount;
}
