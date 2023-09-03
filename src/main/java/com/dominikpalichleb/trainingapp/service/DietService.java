package com.dominikpalichleb.trainingapp.service;

import com.dominikpalichleb.trainingapp.domain.dto.DietDto;
import com.dominikpalichleb.trainingapp.domain.dto.DishDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.Diet;
import com.dominikpalichleb.trainingapp.domain.model.Dish;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.DietRepository;
import com.dominikpalichleb.trainingapp.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DietService {
    private final DietRepository dietRepository;
    private final DishRepository dishRepository;
    private final EntityDtoMapper mapper;
    public void addDiet(DietDto dietDto, User user){
        Diet diet = mapper.toDiet(dietDto, user);
        dietRepository.save(diet);
    }

    public Diet getDietByDate(User user, Date date){
        Diet diet = dietRepository.findByUserIdAndDate(user, date);
        return diet;
    }

    public void addDish(DishDto dishDto){
        Dish dish = mapper.toDish(dishDto);
        dishRepository.save(dish);
    }
}
