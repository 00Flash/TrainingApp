package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.ExerciseLog;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseLogRepository extends MongoRepository<ExerciseLog, String> {
    List<ExerciseLog> findAllByUser(User user);
}
