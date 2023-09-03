package com.dominikpalichleb.trainingapp.domain.mapper;

import com.dominikpalichleb.trainingapp.domain.dto.*;
import com.dominikpalichleb.trainingapp.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityDtoMapper {

    public DietDto toDietDto(Diet diet) {
        return DietDto.builder()
                .dishes(diet.getDishes())
                .date(diet.getDate())
                .build();
    }

    public Diet toDiet(DietDto dietDto, User user) {
        return Diet.builder()
                .user(user)
                .dishes(dietDto.getDishes())
                .date(dietDto.getDate())
                .build();
    }

    public ExerciseDto toExcerciseDto(Exercise exercise){
        return ExerciseDto.builder()
                .name(exercise.getName())
                .reps(exercise.getReps())
                .unit(exercise.getUnit())
                .value(exercise.getValue())
                .secondUnit(exercise.getSecondUnit())
                .build();
    }

    public Exercise toExcercise(ExerciseDto exerciseDto, User user){
        Exercise exercise = Exercise.builder()
                .user(user)
                .unit(exerciseDto.getUnit())
                .secondUnit(exerciseDto.getSecondUnit())
                .value(exerciseDto.getValue())
                .name(exerciseDto.getName())
                .reps(exerciseDto.getReps())
                .build();
        return exercise;
    }

    public ExerciseLogDto toExcerciseLogDto(ExerciseLog exerciseLog){
        return ExerciseLogDto.builder()
                .exercise(exerciseLog.getExercise())
                .date(exerciseLog.getDate())
                .build();
    }

    public ExerciseLog toExcerciseLog(ExerciseLogDto exerciseLogDto, User user){
        return ExerciseLog.builder()
                .exercise(exerciseLogDto.getExercise())
                .user(user)
                .date(exerciseLogDto.getDate())
                .build();
    }

    public TrainingSessionDto toTrainingSessionDto(TrainingSession trainingSession){
        return TrainingSessionDto.builder()
                .exercises(trainingSession.getExercises())
                .name(trainingSession.getName())
                .build();
    }

    public TrainingSession toTrainingSession(TrainingSessionDto trainingSessionDto, User user){
        return TrainingSession.builder()
                .exercises(trainingSessionDto.getExercises())
                .name(trainingSessionDto.getName())
                .user(user)
                .build();
    }

    public DishDto toDishDto(Dish dish){
        return DishDto.builder()
                .fat(dish.getFat())
                .carbon(dish.getCarbon())
                .protein(dish.getProtein())
                .kcal(dish.getKcal())
                .name(dish.getName())
                .amount(dish.getAmount())
                .build();
    }
    public Dish toDish(DishDto dish){
        return Dish.builder()
                .fat(dish.getFat())
                .carbon(dish.getCarbon())
                .protein(dish.getProtein())
                .kcal(dish.getKcal())
                .name(dish.getName())
                .amount(dish.getAmount())
                .build();
    }
}
