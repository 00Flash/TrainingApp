package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.dto.DietDto;
import com.dominikpalichleb.trainingapp.domain.dto.DishDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.Diet;
import com.dominikpalichleb.trainingapp.domain.model.Dish;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.DietRepository;
import com.dominikpalichleb.trainingapp.repository.DishRepository;
import com.dominikpalichleb.trainingapp.service.DietService;
import com.dominikpalichleb.trainingapp.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diet")
public class DietController {
    private final UserContextService userContextService;
    private final DietRepository dietRepository;
    private final DietService dietService;
    private final DishRepository dishRepository;
    private final EntityDtoMapper mapper;

    @GetMapping
    public String showAllDietDays(Model model) {
        User loggedUser = userContextService.getLoggedUser();

        List<Diet> dietList = dietRepository.findAllByUserId(loggedUser.getId());
        List<Dish> dishList = dishRepository.findAll();
        List<DietDto> dietDtoList = new ArrayList<>();
        List<DishDto> dishDtoList = new ArrayList<>();
        for (int i=0; i<dietList.size(); i++){
            dietDtoList.add(mapper.toDietDto(dietList.get(i)));
        }
        for (int i=0; i<dishList.size(); i++){
            dishDtoList.add(mapper.toDishDto(dishList.get(i)));
        }

        DishDto dish = new DishDto();
        Date date = new Date();
        Diet diet = dietService.getDietByDate(loggedUser, date);
        model.addAttribute("dish", dish);
        model.addAttribute("dietDay", diet);
        model.addAttribute("dishList", dishDtoList);
        model.addAttribute("dietList", dietDtoList);
        return "diet-home";
    }
    @PostMapping
    public String addDishToDietDay() {

        return "diet-home";
    }

    @PostMapping("/custom")
    public String addCustomDishToDietDay(@ModelAttribute DishDto dish) {
        dietService.addDish(dish);
        return "diet-home";
    }
    @GetMapping("/search-dish/{name}")
    public String searchDish(@PathVariable String name, Model model){
        List<Dish> allDishes = dishRepository.findAll();
        List<Dish> dishList = new ArrayList<>();
        for (int i=0; i<allDishes.size(); i++){
            if (allDishes.get(i).getName().contains(name))
                dishList.add(allDishes.get(i));
        }
        model.addAttribute("dishList", dishList);
        return "search-dish";
    }
}
