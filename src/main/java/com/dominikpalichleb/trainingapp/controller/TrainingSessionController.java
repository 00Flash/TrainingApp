package com.dominikpalichleb.trainingapp.controller;

import com.dominikpalichleb.trainingapp.domain.dto.ExerciseDto;
import com.dominikpalichleb.trainingapp.domain.dto.ExerciseLogDto;
import com.dominikpalichleb.trainingapp.domain.dto.TrainingSessionDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.service.TrainingSessionService;
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
@RequestMapping("/training")
public class TrainingSessionController {
    private final UserContextService userContextService;
    private final TrainingSessionService trainingSessionService;
    private final EntityDtoMapper mapper;

    @GetMapping
    public String showAllTrainingSessions(Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<TrainingSessionDto> trainingSessionList = trainingSessionService.getTrainingSessionsByUser(loggedUser);
        ExerciseDto exerciseDto = new ExerciseDto();
        model.addAttribute("toExerciseLog", exerciseDto);
        model.addAttribute("trainingSessionList", trainingSessionList);
        return "training-sessions";
    }

    @GetMapping("/session/{name}")
    public String showOneTrainingSession(@PathVariable String name, Model model){
        User loggedUser = userContextService.getLoggedUser();
        List<ExerciseDto> exerciseDtoList = trainingSessionService.getExcercisesByUser(loggedUser);
        TrainingSessionDto trainingSessionDto = trainingSessionService.getTrainingSessionByName(loggedUser, name);
        String oldName = trainingSessionDto.getName();
        model.addAttribute("oldName", oldName);
        model.addAttribute("allExercises", exerciseDtoList);
        model.addAttribute("trainingSessionDto", trainingSessionDto);
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
    public String saveChangesInTrainingSession(@ModelAttribute TrainingSessionDto trainingSessionDto, @RequestParam String action){
        User loggedUser = userContextService.getLoggedUser();
        if (action.equals("Save"))
            trainingSessionService.updateTrainingSession(trainingSessionDto, loggedUser);
        else {
            for (int i=0; i<trainingSessionDto.getExercises().size(); i++){
                if (trainingSessionDto.getExercises().get(i).getName().equals(action))
                    trainingSessionDto.getExercises().remove(i);
            }
            trainingSessionService.updateTrainingSession(trainingSessionDto, loggedUser);
        }
        return "redirect:/training";
    }

    @PostMapping("/exercise/form")
    public String processExerciseForm(@ModelAttribute ExerciseDto exerciseDto){
        User loggedUser = userContextService.getLoggedUser();
        String s = exerciseDto.getName();
        s = s.replaceAll("\\s", "");
        exerciseDto.setName(s);
        trainingSessionService.addExercise(exerciseDto, loggedUser);
        return "redirect:/training/exercises";
    }

    @PostMapping("/add-log")
    public String addToLog(@ModelAttribute ExerciseDto exerciseDto){
        ExerciseLogDto exerciseLogDto = new ExerciseLogDto();
        User loggedUser = userContextService.getLoggedUser();
        LocalDateTime today = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(today);
        exerciseLogDto.setExercise(mapper.toExcercise(exerciseDto, loggedUser));
        exerciseLogDto.setDate(date);
        trainingSessionService.addExerciseLog(exerciseLogDto, loggedUser);
        return "redirect:/training";
    }

    @GetMapping("/exercise-log/{name}")
    public String showExerciseLog(@PathVariable String name, Model model){
        System.out.println("jestem");
        System.out.println(name);
        User loggedUser = userContextService.getLoggedUser();
        ExerciseDto exerciseDto = trainingSessionService.getExercise(name, loggedUser);
        List<ExerciseLogDto> list = trainingSessionService.getExcerciseLogsByUser(loggedUser, exerciseDto);
        model.addAttribute("exercise", exerciseDto);
        model.addAttribute("logs", list);

        return "exercise-log";
    }
}
