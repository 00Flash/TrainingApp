package com.dominikpalichleb.trainingapp.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Dish {
    private int kcal;
    private int fat;
    private int protein;
    private int carbon;
    private String name;
    private int amount;
}
