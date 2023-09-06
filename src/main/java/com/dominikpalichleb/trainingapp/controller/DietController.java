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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diet")
public class DietController {
    private final UserContextService userContextService;
    private final DietService dietService;
    private final EntityDtoMapper mapper;
    @GetMapping
    public String showAllDietDays(Model model) {
        User loggedUser = userContextService.getLoggedUser();

        List<DietDto> dietList = dietService.getAllDiets(loggedUser);
        List<DishDto> dishList = dietService.getAllDishes();

        DishDto dish = new DishDto();
        dish.setName("");
        dish.setAmount(0);
        dish.setKcal(0);
        dish.setCarbon(0);
        dish.setProtein(0);
        dish.setFat(0);
        model.addAttribute("dish", dish);
        model.addAttribute("dishList", dishList);
        model.addAttribute("dietList", dietList);
        return "diet-home";
    }
    @PostMapping
    public String addDishToDietDay(@RequestParam(value = "seach-dish-name") String name, @RequestParam(value = "seach-dish-amount") int amount, @RequestParam(value = "search-dish-date") String date) {
        User loggedUser = userContextService.getLoggedUser();
        DietDto diet = dietService.getDietByDate(loggedUser, date);
        System.out.println(diet);
        DishDto dish = dietService.getDishByName(name);
        dish.setAmount(amount);
        diet.addDish(mapper.toDish(dish));
        diet.countNumbers();
        dietService.updateDiet(diet, loggedUser);

        return "redirect:/diet";
    }

    @PostMapping("/removeDish")
    public String removeDishFromDiet(@ModelAttribute DishDto dish, @RequestParam(value = "removal-dish-name") String date){
        System.out.println(dish);
        System.out.println(date);
        return "redirect:/diet";
    }

    @PostMapping("/custom")
    public String addCustomDishToDietDay(@ModelAttribute DishDto dish, @RequestParam(value = "custom-dish-date") String date) {
        User loggedUser = userContextService.getLoggedUser();
        DietDto diet = dietService.getDietByDate(loggedUser, date);
        diet.addDish(mapper.toDish(dish));
        diet.countNumbers();
        dietService.updateDiet(diet, loggedUser);
        return "redirect:/diet";
    }
    @GetMapping("/search-dish/{name}")
    public String searchDish(@PathVariable String name, Model model){
        List<DishDto> allDishes = dietService.getAllDishes();
        List<DishDto> dishList = new ArrayList<>();
        for (int i=0; i<allDishes.size(); i++){
            if (allDishes.get(i).getName().contains(name))
                dishList.add(allDishes.get(i));
        }
        model.addAttribute("dishList", dishList);
        return "search-dish";
    }
    @PostMapping("/new-day")
    public String createNewDietDay(){
        User loggedUser = userContextService.getLoggedUser();
        DietDto dietDto = new DietDto();
        LocalDateTime date = LocalDateTime.now();
        dietDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(date));
        dietService.addDiet(dietDto, loggedUser);
        return "redirect:/diet";
    }
}
