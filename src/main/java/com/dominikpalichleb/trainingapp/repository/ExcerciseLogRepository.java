package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.ExcerciseLog;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExcerciseLogRepository extends MongoRepository<ExcerciseLog, String> {
    Optional<ExcerciseLog> findAllByUser(User user);
}
