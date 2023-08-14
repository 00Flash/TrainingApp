package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Excercise {
    @Id
    private Long id;
    private User user;
    private String name;
    private String unit;
    private int reps;
    private int value;

}
