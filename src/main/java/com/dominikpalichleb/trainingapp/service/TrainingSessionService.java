package com.dominikpalichleb.trainingapp.service;

import com.dominikpalichleb.trainingapp.domain.dto.ExerciseDto;
import com.dominikpalichleb.trainingapp.domain.dto.ExerciseLogDto;
import com.dominikpalichleb.trainingapp.domain.dto.TrainingSessionDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.Exercise;
import com.dominikpalichleb.trainingapp.domain.model.ExerciseLog;
import com.dominikpalichleb.trainingapp.domain.model.TrainingSession;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.ExerciseLogRepository;
import com.dominikpalichleb.trainingapp.repository.ExerciseRepository;
import com.dominikpalichleb.trainingapp.repository.TrainingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingSessionService {
    private final TrainingSessionRepository trainingSessionRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseLogRepository exerciseLogRepository;
    private final EntityDtoMapper mapper;

    public void addExercise(ExerciseDto exerciseDto, User user){
        Exercise exercise = mapper.toExcercise(exerciseDto, user);
        int s = 0;
        String name = exercise.getName();
        while (!exerciseRepository.findByNameAndUser(exercise.getName(), user).isEmpty()){
            s++;
            name = exercise.getName() + s;
        }
        exercise.setName(name);
        exerciseRepository.save(exercise);
    }

    public ExerciseDto getExercise(String name, User user){
        return mapper.toExcerciseDto(exerciseRepository.findByNameAndUser(name, user).get(0));
    }

    public void addExcerciseLog(ExerciseLogDto exerciseLogDto, User user){
        ExerciseLog exerciseLog = mapper.toExcerciseLog(exerciseLogDto, user);
        exerciseLogRepository.save(exerciseLog);
    }

    public void addTrainingSession(TrainingSessionDto trainingSessionDto, User user){
        TrainingSession trainingSession = mapper.toTrainingSession(trainingSessionDto, user);
        String name;
        int s = 0;
        do {
            s = s+1;
            name = "Training day " + s;
        }while (!trainingSessionRepository.findByNameAndUser(name, user).isEmpty());
        trainingSession.setName(name);
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setName("first exercise");
        exerciseDto.setReps(0);
        exerciseDto.setUnit("reps");
        exerciseDto.setValue(0);
        exerciseDto.setSecondUnit("kg");
        List<Exercise> list = new ArrayList<>();
        list.add(mapper.toExcercise(exerciseDto, user));
        trainingSession.setExercises(list);
        trainingSessionRepository.save(trainingSession);
    }

    public void deleteTrainingSession(String name, User user){
        trainingSessionRepository.deleteByNameAndUser(name, user);
    }
    public void updateTrainingSession(TrainingSessionDto trainingSessionDto, User user){
        this.deleteTrainingSession(trainingSessionDto.getName(), user);
        trainingSessionRepository.save(mapper.toTrainingSession(trainingSessionDto, user));
    }

    public TrainingSessionDto getTrainingSessionByName(User user, String name){
        List<TrainingSession> trainingSession = trainingSessionRepository.findByNameAndUser(name, user);
        if (trainingSession.isEmpty()) return new TrainingSessionDto();
        else {
            return mapper.toTrainingSessionDto(trainingSession.get(0));
        }
    }

    public List<ExerciseDto> getExcercisesByUser(User user){
        List<Exercise> exercises = exerciseRepository.findAllByUser(user);
        List<ExerciseDto> finalList = new ArrayList<>();
        for(int i = 0; i< exercises.size(); i++){
            finalList.add(mapper.toExcerciseDto(exercises.get(i)));
        }
        return finalList;
    }

    public List<TrainingSessionDto> getTrainingSessionsByUser(User user){
        List<TrainingSession> list = trainingSessionRepository.findAllByUser(user);
        List<TrainingSessionDto> finalList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            finalList.add(mapper.toTrainingSessionDto(list.get(i)));
        }
        return finalList;
    }

    public List<ExerciseLogDto> getExcerciseLogsByUserAndExcercise(User user, Exercise exercise){
        Optional<ExerciseLog> optionalList = exerciseLogRepository.findAllByUser(user);
        List<ExerciseLogDto> finalList = null;
        List<ExerciseLog> exerciseLogList = optionalList.stream().toList();
        for(int i = 0; i< exerciseLogList.size(); i++){
            finalList.add(mapper.toExcerciseLogDto(exerciseLogList.get(i)));
        }
        return finalList;
    }
}
