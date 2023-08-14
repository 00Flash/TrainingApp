package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.Excercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExcerciseRepository extends MongoRepository<Excercise, String> {
}
