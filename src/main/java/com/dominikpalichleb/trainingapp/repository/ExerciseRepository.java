package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.Exercise;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.lang.model.element.Name;
import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> findAllByUser(User user);
    List<Exercise> findByNameAndUser(String name, User user);
}
