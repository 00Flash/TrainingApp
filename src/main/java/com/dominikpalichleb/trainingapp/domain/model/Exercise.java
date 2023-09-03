package com.dominikpalichleb.trainingapp.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Exercise {
    private User user;
    private String name;
    private String unit;
    private int reps;
    private int value;
    private String secondUnit;

}
