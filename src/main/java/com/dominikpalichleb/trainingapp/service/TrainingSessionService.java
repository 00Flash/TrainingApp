package com.dominikpalichleb.trainingapp.service;

import com.dominikpalichleb.trainingapp.domain.dto.ExcerciseDto;
import com.dominikpalichleb.trainingapp.domain.dto.ExcerciseLogDto;
import com.dominikpalichleb.trainingapp.domain.dto.TrainingSessionDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.Excercise;
import com.dominikpalichleb.trainingapp.domain.model.ExcerciseLog;
import com.dominikpalichleb.trainingapp.domain.model.TrainingSession;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.ExcerciseLogRepository;
import com.dominikpalichleb.trainingapp.repository.ExcerciseRepository;
import com.dominikpalichleb.trainingapp.repository.TrainingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingSessionService {
    private final TrainingSessionRepository trainingSessionRepository;
    private final ExcerciseRepository excerciseRepository;
    private final ExcerciseLogRepository excerciseLogRepository;
    private final EntityDtoMapper mapper;

    public void addExcercise(ExcerciseDto excerciseDto, User user){
        Excercise excercise = mapper.toExcercise(excerciseDto, user);
        excerciseRepository.save(excercise);
    }

    public void addExcerciseLog(ExcerciseLogDto excerciseLogDto, User user){
        ExcerciseLog excerciseLog = mapper.toExcerciseLog(excerciseLogDto, user);
        excerciseLogRepository.save(excerciseLog);
    }

    public void addTrainingSession(TrainingSessionDto trainingSessionDto, User user){
        TrainingSession trainingSession = mapper.toTrainingSession(trainingSessionDto, user);
        trainingSessionRepository.save(trainingSession);
    }

    public List<ExcerciseDto> getExcercisesByUser(User user){
        Optional<Excercise> optionalList = excerciseRepository.findAllByUser(user);
        List<ExcerciseDto> finalList = null;
        List<Excercise> excerciseList = optionalList.stream().toList();
        for(int i=0; i<excerciseList.size(); i++){
            finalList.add(mapper.toExcerciseDto(excerciseList.get(i)));
        }
        return finalList;
    }

    public List<TrainingSessionDto> getTrainingSessionsByUser(User user){
        Optional<TrainingSession> optionalList = trainingSessionRepository.findAllByUser(user);
        List<TrainingSessionDto> finalList = null;
        List<TrainingSession> trainingSessionsList = optionalList.stream().toList();
        for(int i=0; i<trainingSessionsList.size(); i++){
            finalList.add(mapper.toTrainingSessionDto(trainingSessionsList.get(i)));
        }
        return finalList;
    }

    public List<ExcerciseLogDto> getExcerciseLogsByUserAndExcercise(User user, Excercise excercise){
        Optional<ExcerciseLog> optionalList = excerciseLogRepository.findAllByUser(user);
        List<ExcerciseLogDto> finalList = null;
        List<ExcerciseLog> excerciseLogList = optionalList.stream().toList();
        for(int i=0; i<excerciseLogList.size(); i++){
            finalList.add(mapper.toExcerciseLogDto(excerciseLogList.get(i)));
        }
        return finalList;
    }
}
