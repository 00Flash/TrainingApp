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
                .userId(diet.getUserId())
                .dishes(diet.getDishes())
                .date(diet.getDate())
                .build();
    }

    public Diet toDiet(DietDto dietDto) {
        return Diet.builder()
                .id(dietDto.getId())
                .userId(dietDto.getUserId())
                .dishes(dietDto.getDishes())
                .date(dietDto.getDate())
                .build();
    }


}
