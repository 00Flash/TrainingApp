package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
public class TrainingSession {
    @Id
    private String id;
    private User user;
    private String name;
    private List<Exercise> exercises;
}
