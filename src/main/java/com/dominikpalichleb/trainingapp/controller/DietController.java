package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.dto.DietDto;
import com.dominikpalichleb.trainingapp.domain.dto.DishDto;
import com.dominikpalichleb.trainingapp.domain.model.Diet;
import com.dominikpalichleb.trainingapp.domain.model.Dish;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.DietRepository;
import com.dominikpalichleb.trainingapp.service.DietService;
import com.dominikpalichleb.trainingapp.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diet")
public class DietController {
    private final UserContextService userContextService;
    private final DietRepository dietRepository;
    private final DietService dietService;
    @GetMapping
    public String showAllDietDays(Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<Diet> dietList = dietRepository.findAllByUserId(loggedUser.getId());
        model.addAttribute("dietList", dietList);
        return "diet";
    }

    @GetMapping("/dietForm")
    public String addDietForm(Model model){
        DishDto dishDto = new DishDto();
        Date date = new Date();
        User loggedUser = userContextService.getLoggedUser();
        DietDto diet = dietService.getDietByDate(loggedUser, date);
        model.addAttribute("dietDay", diet);
        model.addAttribute("dish", dishDto);

        return "addDishToDiet";
    }

}
