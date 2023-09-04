package com.dominikpalichleb.trainingapp.domain.dto;

import com.dominikpalichleb.trainingapp.domain.model.Exercise;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSessionDto {
    @Id
    private String id;
    private List<Exercise> exercises;
    private String name;
}
