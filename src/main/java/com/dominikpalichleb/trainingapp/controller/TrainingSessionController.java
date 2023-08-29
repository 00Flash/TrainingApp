package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.dto.ExcerciseDto;
import com.dominikpalichleb.trainingapp.domain.dto.ExcerciseLogDto;
import com.dominikpalichleb.trainingapp.domain.dto.TrainingSessionDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.service.TrainingSessionService;
import com.dominikpalichleb.trainingapp.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/training")
public class TrainingSessionController {
    private final UserContextService userContextService;
    private final TrainingSessionService trainingSessionService;
    private final EntityDtoMapper mapper;

    @GetMapping
    public String showAllTrainingSessions(Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<TrainingSessionDto> trainingSessionList = trainingSessionService.getTrainingSessionsByUser(loggedUser);
        model.addAttribute("trainingSessionList", trainingSessionList);
        return "";
    }

    @GetMapping("/session")
    public String showOneTrainingSession(Model model){

        return "";
    }

    @GetMapping("/excercises")
    public String showAllExcercises(Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<ExcerciseDto> excerciseList = trainingSessionService.getExcercisesByUser(loggedUser);
        model.addAttribute("exercisesList", excerciseList);
        return "";
    }

   /* @PostMapping
    public String showExcerciseLogs(@ModelAttribute ExcerciseDto excerciseDto, Model model){ //TODO tesotwanie, alternatywna wersja: @ModelAttribute("exercise") @Valid ExcerciseDto excerciseDto,
        User loggedUser = userContextService.getLoggedUser();
        List<ExcerciseLogDto> excerciseLogList = trainingSessionService.getExcerciseLogsByUserAndExcercise(loggedUser, mapper.toExcercise(excerciseDto, loggedUser));
        model.addAttribute("excercise", excerciseDto);
        model.addAttribute("exerciseLogList", excerciseLogList);
        return "";
    }*/

    @GetMapping("/session/form")
    public String showTrainingSessionForm(Model model){
        TrainingSessionDto trainingSessionDto = new TrainingSessionDto();
        User loggedUser = userContextService.getLoggedUser();
        model.addAttribute("trainingSession", trainingSessionDto);
        model.addAttribute("loggedUser", loggedUser);
        return "";
    }

    @GetMapping("/excercise/form")
    public String showExcerciseForm(Model model){
        ExcerciseDto excercise = new ExcerciseDto();
        User loggedUser = userContextService.getLoggedUser();
        model.addAttribute("exercise", excercise);
        model.addAttribute("loggedUser", loggedUser);
        return "";
    }

    @PostMapping
    public String processTrainingSessionForm(@ModelAttribute TrainingSessionDto trainingSessionDto){
        User loggedUser = userContextService.getLoggedUser();
        trainingSessionService.addTrainingSession(trainingSessionDto, loggedUser);
        return "";
    }

   /* @PostMapping
    public String processExerciseForm(@ModelAttribute ExcerciseDto excerciseDto){
        User loggedUser = userContextService.getLoggedUser();
        trainingSessionService.addExcercise(excerciseDto, loggedUser);
        return "";
    }*/
}
