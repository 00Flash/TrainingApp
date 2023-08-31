package com.dominikpalichleb.trainingapp.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Dish {
    private String kcal;
    private String fat;
    private String protein;
    private String carbon;
    private String name;
}
