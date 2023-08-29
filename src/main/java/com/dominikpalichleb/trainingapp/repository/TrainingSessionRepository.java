package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.TrainingSession;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TrainingSessionRepository extends MongoRepository<TrainingSession, String> {
    Optional<TrainingSession> findAllByUser(User user);
}
