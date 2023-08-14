package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document
public class ExcerciseLog {
    @Id
    private Long id;
    private User user;
    private Excercise excercise;
    private LocalDateTime date;

}
