package com.dominikpalichleb.trainingapp.domain.dto;

import com.dominikpalichleb.trainingapp.domain.model.Dish;
import com.dominikpalichleb.trainingapp.domain.model.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietDto {
    @Id
    private String id;
    private List<Dish> dishes;
    private String date;
    private int kcal;
    private int carbs;
    private int fats;
    private int proteins;

    public void countNumbers(){
        kcal=0;
        carbs=0;
        fats=0;
        proteins=0;
        for (int i=0; i<dishes.size(); i++){
            kcal = kcal + (dishes.get(i).getKcal() * dishes.get(i).getAmount() / 100);
            carbs = carbs + (dishes.get(i).getCarbon() * dishes.get(i).getAmount() / 100);
            fats = fats + (dishes.get(i).getFat() * dishes.get(i).getAmount() / 100);
            proteins = proteins + (dishes.get(i).getProtein() * dishes.get(i).getAmount() / 100);
        }
    }
    public void addDish(Dish dish){
        if (dishes == null)
            dishes = new ArrayList<>();
        dishes.add(dish);
    }
}
