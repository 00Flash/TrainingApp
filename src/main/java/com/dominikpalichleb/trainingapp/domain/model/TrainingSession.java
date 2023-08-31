package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
public class TrainingSession {
    private User user;
    private String name;
    private List<Excercise> excercises;
}
