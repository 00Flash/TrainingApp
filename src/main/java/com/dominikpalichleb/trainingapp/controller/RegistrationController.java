package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.dto.UserDto;
import com.dominikpalichleb.trainingapp.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-up")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDto());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping("/success")
    public String showSuccessPage(){
        return "success";
    }

    @PostMapping
    public String processSignUpForm(
            @ModelAttribute("user") @Valid UserDto userDto,
            Errors errors) throws Exception {

        if (errors.hasErrors()) {
            return "register";
        }

        registrationService.register(userDto);

        return "redirect:/sign-up/success";
    }
}