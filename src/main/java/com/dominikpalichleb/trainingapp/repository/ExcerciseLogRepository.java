package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.ExcerciseLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExcerciseLogRepository extends MongoRepository<ExcerciseLog, String> {
}
