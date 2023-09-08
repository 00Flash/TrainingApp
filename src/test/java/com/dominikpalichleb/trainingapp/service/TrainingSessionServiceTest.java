package com.dominikpalichleb.trainingapp.service;

import com.dominikpalichleb.trainingapp.domain.dto.DishDto;
import com.dominikpalichleb.trainingapp.domain.dto.ExerciseDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.Dish;
import com.dominikpalichleb.trainingapp.domain.model.Exercise;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.DishRepository;
import com.dominikpalichleb.trainingapp.repository.ExerciseRepository;
import com.dominikpalichleb.trainingapp.repository.TrainingSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainingSessionServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private EntityDtoMapper mapper;

    @InjectMocks
    private TrainingSessionService trainingSessionService;

    @Captor
    ArgumentCaptor<Exercise> exerciseCaptor;

    @Captor
    ArgumentCaptor<User> userCaptor;
    @Test
    void shouldAddExercise() {
        // given
        final String NAME = "test";
        final String UNIT = "test";
        final int REPS = 0;
        final int VALUE = 0;
        final String SECONDUNIT = "test";
        final User user = createUser();

        ExerciseDto exerciseDto = buildExerciseDto(NAME, UNIT, REPS, VALUE, SECONDUNIT);
        when(mapper.toExcercise(ArgumentMatchers.any(ExerciseDto.class), user)).thenCallRealMethod();
        Exercise exercise = mapper.toExcercise(exerciseDto, user);

        // when
        trainingSessionService.addExercise(exerciseDto, user);

        // then
        Mockito.verify(exerciseRepository, times(1)).save(exerciseCaptor.capture());
        assertEquals(exercise, exerciseCaptor.getValue());
    }

    private static ExerciseDto buildExerciseDto(String name, String unit, int reps, int value, String secondUnit){
        return ExerciseDto.builder()
                .name(name)
                .unit(unit)
                .reps(reps)
                .value(value)
                .secondUnit(secondUnit)
                .build();
    }

    private static User createUser(){
        User user = new User();
        user.setId(0L);
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test");
        return user;
    }
}