package com.dominikpalichleb.trainingapp.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "diet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kcal;
    private String fat;
    private String proteins;
    private String carbon;
    private String dishes;
    private Date date;

}
