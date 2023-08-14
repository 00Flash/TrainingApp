package com.dominikpalichleb.trainingapp.repository;

import com.dominikpalichleb.trainingapp.domain.model.Diet;
import com.dominikpalichleb.trainingapp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DietRepository extends MongoRepository<Diet, Long> {
    List<Diet> findAllByUserId(Long id);
    Diet findByUserIdAndDate(Long id, Date date);
}
