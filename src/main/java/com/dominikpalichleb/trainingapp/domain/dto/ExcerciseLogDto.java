package com.dominikpalichleb.trainingapp.domain.dto;

import com.dominikpalichleb.trainingapp.domain.model.Excercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcerciseLogDto {
    private Excercise excercise;
    private LocalDateTime date;
}
