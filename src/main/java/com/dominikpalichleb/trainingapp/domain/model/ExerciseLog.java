package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class ExerciseLog {
    @Id
    private Long id;
    private User user;
    private Exercise exercise;
    private LocalDateTime date;

}
