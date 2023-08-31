package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Excercise {
    private User user;
    private String name;
    private String unit;
    private int reps;
    private int value;
    private String unit2;

}
