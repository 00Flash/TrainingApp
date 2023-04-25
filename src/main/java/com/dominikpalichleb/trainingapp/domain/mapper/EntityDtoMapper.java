package com.dominikpalichleb.trainingapp.domain.mapper;

import com.dominikpalichleb.trainingapp.domain.dto.DietDto;
import com.dominikpalichleb.trainingapp.domain.model.Diet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityDtoMapper {

    public DietDto toDietDto(Diet diet) {
        return DietDto.builder()
                .id(diet.getId())
                .kcal(diet.getKcal())
                .fat(diet.getFat())
                .proteins(diet.getProteins())
                .carbon(diet.getCarbon())
                .dishes(diet.getDishes())
                .date(diet.getDate())
                .build();
    }

    public Diet toDiet(DietDto dietDto) {
        return Diet.builder()
                .id(dietDto.getId())
                .kcal(dietDto.getKcal())
                .fat(dietDto.getFat())
                .proteins(dietDto.getProteins())
                .carbon(dietDto.getCarbon())
                .dishes(dietDto.getDishes())
                .date(dietDto.getDate())
                .build();
    }


}
