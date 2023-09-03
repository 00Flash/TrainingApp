package com.dominikpalichleb.trainingapp.domain.model;

import com.dominikpalichleb.trainingapp.domain.dto.TrainingSessionDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TrainingSessionsHelper {
    TrainingSessionDto trainingSessionDto;
    String newName;
}
