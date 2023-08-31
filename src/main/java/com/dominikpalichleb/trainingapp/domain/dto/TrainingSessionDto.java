package com.dominikpalichleb.trainingapp.domain.dto;

import com.dominikpalichleb.trainingapp.domain.model.Excercise;
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
    private List<Excercise> excercises;
    private String name;
}
