package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.TrainingSession;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingSessionRepository extends MongoRepository<TrainingSession, String> {
    List<TrainingSession> findAllByUser(User user);
    List<TrainingSession> findByNameAndUser(String name, User user);
    List<TrainingSession> deleteByNameAndUser(String name, User user);
}
