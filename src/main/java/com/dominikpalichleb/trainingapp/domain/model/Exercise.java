package com.dominikpalichleb.trainingapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private User user;
    private String name;
    private String unit;
    private int reps;
    private int value;
    private String secondUnit;

}
