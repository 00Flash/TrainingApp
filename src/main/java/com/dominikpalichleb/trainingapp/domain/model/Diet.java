package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
// TODO: 09.06.2023  dodać adnotacje do mongo
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private List<Dish> dishes;
    private Date date;

}
