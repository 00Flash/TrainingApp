package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Builder
@Data
public class Diet {
    @Id
    private String id;
    private User user;
    private List<Dish> dishes;
    private String date;
    private int kcal;
    private int carbs;
    private int fats;
    private int proteins;

}
