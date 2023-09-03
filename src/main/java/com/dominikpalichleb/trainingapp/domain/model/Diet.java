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
    private User user;
    private List<Dish> dishes;
    private Date date;

}
