package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.Excercise;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExcerciseRepository extends MongoRepository<Excercise, String> {
    Optional<Excercise> findAllByUser(User user);
}
