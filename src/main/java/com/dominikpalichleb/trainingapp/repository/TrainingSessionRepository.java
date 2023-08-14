package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.TrainingSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingSessionRepository extends MongoRepository<TrainingSession, String> {
}
