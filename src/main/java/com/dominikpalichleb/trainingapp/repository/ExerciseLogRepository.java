package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.ExerciseLog;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExcerciseLogRepository extends MongoRepository<ExerciseLog, String> {
    Optional<ExerciseLog> findAllByUser(User user);
}
