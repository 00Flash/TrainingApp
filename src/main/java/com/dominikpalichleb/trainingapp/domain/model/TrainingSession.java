package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class TrainingSession {
    @Id
    private Long id;
    private User user;
    private List<Excercise> excercises;
}
