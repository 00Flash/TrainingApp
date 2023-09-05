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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietService {
    private final DietRepository dietRepository;
    private final DishRepository dishRepository;
    private final EntityDtoMapper mapper;
    public void addDiet(DietDto dietDto, User user){
        Diet diet = mapper.toDiet(dietDto, user);
        List<Diet> dietList = dietRepository.findByUserAndDate(user, dietDto.getDate());
        int s = 0;
        String date = diet.getDate();
        while (!dietList.isEmpty()){
            s++;
            date = dietDto.getDate() + " (" + s +")";
            dietList = dietRepository.findByUserAndDate(user, date);
        }
        diet.setDate(date);
        dietRepository.save(diet);
    }

    public Diet getDietByDate(User user, String date){
        List<Diet> diet = dietRepository.findByUserAndDate(user, date);
        return diet.get(0);
    }

    public List<DietDto> getAllDiets(User user){
        List<Diet> dietList = dietRepository.findAllByUser(user);
        List<DietDto> dietDtoList = new ArrayList<>();
        for (int i=0; i<dietList.size(); i++){
            dietDtoList.add(mapper.toDietDto(dietList.get(i)));
        }
        return dietDtoList;
    }

    public void addDish(DishDto dishDto){
        Dish dish = mapper.toDish(dishDto);
        dishRepository.save(dish);
    }

    public List<DishDto> getAllDishes(){
        List<Dish> dishList = dishRepository.findAll();
        List<DishDto> dishDtoList = new ArrayList<>();
        for (int i=0; i<dishList.size(); i++){
            dishDtoList.add(mapper.toDishDto(dishList.get(i)));
        }
        return dishDtoList;
    }
}
