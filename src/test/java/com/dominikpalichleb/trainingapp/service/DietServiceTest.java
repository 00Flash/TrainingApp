package com.dominikpalichleb.trainingapp.service;

import com.dominikpalichleb.trainingapp.domain.dto.DishDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.Dish;
import com.dominikpalichleb.trainingapp.repository.DietRepository;
import com.dominikpalichleb.trainingapp.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.attribute.standard.MediaSize;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DietServiceTest {

    @Mock
    private DishRepository dishRepository;

    @Mock
    private EntityDtoMapper mapper;

    @InjectMocks
    private DietService dietService;

    @Captor
    ArgumentCaptor<Dish> dishCaptor;

    @Test
    void shouldAddDish() {
        // given
        final int KCAL = 100;
        final int FAT = 10;
        final int PROTEIN = 15;
        final int CARBON = 20;
        final String NAME = "example dish";
        final int AMOUNT = 1;

        DishDto dishDto = buildDishDto(KCAL, FAT, PROTEIN, CARBON, NAME, AMOUNT);
        when(mapper.toDish(ArgumentMatchers.any(DishDto.class))).thenCallRealMethod();
        Dish dish = mapper.toDish(dishDto);

        // when
        dietService.addDish(dishDto);

        // then
        Mockito.verify(dishRepository, times(1)).save(dishCaptor.capture());
        assertEquals(dish, dishCaptor.getValue());
    }

    private static DishDto buildDishDto(int kcal, int fat, int protein, int carbon, String name, int amount) {
        return DishDto.builder()
                .kcal(kcal)
                .fat(fat)
                .protein(protein)
                .carbon(carbon)
                .name(name)
                .amount(amount)
                .build();

    }

}