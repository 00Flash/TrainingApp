package com.dominikpalichleb.trainingapp.domain.mapper;

import com.dominikpalichleb.trainingapp.domain.dto.DietDto;
import com.dominikpalichleb.trainingapp.domain.dto.ExcerciseDto;
import com.dominikpalichleb.trainingapp.domain.dto.ExcerciseLogDto;
import com.dominikpalichleb.trainingapp.domain.dto.TrainingSessionDto;
import com.dominikpalichleb.trainingapp.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityDtoMapper {

    public DietDto toDietDto(Diet diet) {
        return DietDto.builder()
                .id(diet.getId())
                .userId(diet.getUserId())
                .dishes(diet.getDishes())
                .date(diet.getDate())
                .build();
    }

    public Diet toDiet(DietDto dietDto) {
        return Diet.builder()
                .id(dietDto.getId())
                .userId(dietDto.getUserId())
                .dishes(dietDto.getDishes())
                .date(dietDto.getDate())
                .build();
    }

    public ExcerciseDto toExcerciseDto(Excercise excercise){
        return ExcerciseDto.builder()
                .name(excercise.getName())
                .reps(excercise.getReps())
                .unit(excercise.getUnit())
                .value(excercise.getValue())
                .unit2(excercise.getUnit2())
                .build();
    }

    public Excercise toExcercise(ExcerciseDto excerciseDto, User user){
        return Excercise.builder()
                .user(user)
                .unit(excerciseDto.getUnit())
                .unit2(excerciseDto.getUnit2())
                .value(excerciseDto.getValue())
                .name(excerciseDto.getName())
                .reps(excerciseDto.getReps())
                .build();
    }

    public ExcerciseLogDto toExcerciseLogDto(ExcerciseLog excerciseLog){
        return ExcerciseLogDto.builder()
                .excercise(excerciseLog.getExcercise())
                .date(excerciseLog.getDate())
                .build();
    }

    public ExcerciseLog toExcerciseLog(ExcerciseLogDto excerciseLogDto, User user){
        return ExcerciseLog.builder()
                .excercise(excerciseLogDto.getExcercise())
                .user(user)
                .date(excerciseLogDto.getDate())
                .build();
    }

    public TrainingSessionDto toTrainingSessionDto(TrainingSession trainingSession){
        return TrainingSessionDto.builder()
                .excercises(trainingSession.getExcercises())
                .name(trainingSession.getName())
                .build();
    }

    public TrainingSession toTrainingSession(TrainingSessionDto trainingSessionDto, User user){
        return TrainingSession.builder()
                .excercises(trainingSessionDto.getExcercises())
                .name(trainingSessionDto.getName())
                .user(user)
                .build();
    }
}
