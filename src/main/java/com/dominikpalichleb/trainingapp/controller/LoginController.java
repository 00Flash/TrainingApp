package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserContextService userContextService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {

        User loggedUser = userContextService.getLoggedUser();
        model.addAttribute("user", loggedUser);

        return "home";
    }
}