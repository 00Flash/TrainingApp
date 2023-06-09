package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.model.Diet;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.DietRepository;
import com.dominikpalichleb.trainingapp.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DietController {
    private final UserContextService userContextService;
    private final DietRepository dietRepository;
    @GetMapping("/diet")
    public String showAllDietDays(Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<Diet> dietList = dietRepository.findAllByUserId(loggedUser);
        model.addAttribute("dietList", dietList);
        return "diet";
    }

}
