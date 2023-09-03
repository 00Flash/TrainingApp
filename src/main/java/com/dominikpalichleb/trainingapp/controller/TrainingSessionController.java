package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.dto.ExerciseDto;
import com.dominikpalichleb.trainingapp.domain.dto.TrainingSessionDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.TrainingSessionsHelper;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.service.TrainingSessionService;
import com.dominikpalichleb.trainingapp.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "training-sessions";
    }

    @GetMapping("/session/{name}")
    public String showOneTrainingSession(@PathVariable String name, Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<ExerciseDto> exerciseDtoList = trainingSessionService.getExcercisesByUser(loggedUser);
        TrainingSessionDto trainingSessionDto = trainingSessionService.getTrainingSessionByName(loggedUser, name);
        TrainingSessionsHelper helper = new TrainingSessionsHelper(trainingSessionDto, new String());
        model.addAttribute("allExercises", exerciseDtoList);
        model.addAttribute("trainingSession", helper);
        return "training-session-edit";
    }

    @GetMapping("/exercises")
    public String showAllExercises(Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<ExerciseDto> excerciseList = trainingSessionService.getExcercisesByUser(loggedUser);
        model.addAttribute("exercisesList", excerciseList);
        return "exercises";
    }

   /* @PostMapping
    public String showExcerciseLogs(@ModelAttribute ExerciseDto excerciseDto, Model model){ //TODO tesotwanie, alternatywna wersja: @ModelAttribute("exercise") @Valid ExerciseDto excerciseDto,
        User loggedUser = userContextService.getLoggedUser();
        List<ExerciseLogDto> excerciseLogList = trainingSessionService.getExcerciseLogsByUserAndExcercise(loggedUser, mapper.toExcercise(excerciseDto, loggedUser));
        model.addAttribute("exercise", excerciseDto);
        model.addAttribute("exerciseLogList", excerciseLogList);
        return "";
    }*/

    @PostMapping("/session/{name}/add-exercise")
    public String addExerciseToTrainingSession(@ModelAttribute ExerciseDto exerciseDto, @PathVariable String name){
        User loggedUser = userContextService.getLoggedUser();
        TrainingSessionDto trainingSessionDto = trainingSessionService.getTrainingSessionByName(loggedUser, name);
        exerciseDto = trainingSessionService.getExercise(exerciseDto.getName(), loggedUser);
        trainingSessionDto.getExercises().add(mapper.toExcercise(exerciseDto, loggedUser));
        trainingSessionService.updateTrainingSession(trainingSessionDto, loggedUser);
        return "redirect:/training";
    }

    @GetMapping("/session/form")
    public String showTrainingSessionForm(Model model){
        TrainingSessionDto trainingSessionDto = new TrainingSessionDto();
        User loggedUser = userContextService.getLoggedUser();
        model.addAttribute("trainingSession", trainingSessionDto);
        model.addAttribute("loggedUser", loggedUser);
        return "";
    }

    @GetMapping("/exercise/form")
    public String showExerciseForm(Model model){
        ExerciseDto excercise = new ExerciseDto();
        User loggedUser = userContextService.getLoggedUser();
        model.addAttribute("exercise", excercise);
        model.addAttribute("loggedUser", loggedUser);
        return "exercise-form";
    }

    @PostMapping
    public String processTrainingSessionForm(){
        TrainingSessionDto trainingSessionDto = new TrainingSessionDto();
        User loggedUser = userContextService.getLoggedUser();
        trainingSessionService.addTrainingSession(trainingSessionDto, loggedUser);
        return "redirect:/training";
    }

    @PostMapping("/save-changes")
    public String saveChangesInTrainingSession(@ModelAttribute TrainingSessionsHelper trainingSessionsHelper){
        System.out.println("new name: " + trainingSessionsHelper.getNewName());
        System.out.println("reps: " + trainingSessionsHelper.getTrainingSessionDto().getExercises().get(0).getReps());
        System.out.println("value: " + trainingSessionsHelper.getTrainingSessionDto().getExercises().get(0).getValue());
        return "redirect:/training";
    }

    @PostMapping("/exercise/form")
    public String processExerciseForm(@ModelAttribute ExerciseDto exerciseDto){
        User loggedUser = userContextService.getLoggedUser();
        trainingSessionService.addExercise(exerciseDto, loggedUser);
        return "redirect:/training/exercises";
    }
}
