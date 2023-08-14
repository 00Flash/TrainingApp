package com.dominikpalichleb.trainingapp.service;

import com.dominikpalichleb.trainingapp.domain.dto.DietDto;
import com.dominikpalichleb.trainingapp.domain.mapper.EntityDtoMapper;
import com.dominikpalichleb.trainingapp.domain.model.Diet;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.DietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DietService {
    private final DietRepository dietRepository;
    private final EntityDtoMapper mapper;
    public void addDiet(DietDto dietDto){
        Diet diet = mapper.toDiet(dietDto);
        dietRepository.save(diet);
    }

    public DietDto getDietByDate(User user, Date date){
        Diet diet = dietRepository.findByUserIdAndDate(user.getId(), date);
        return mapper.toDietDto(diet);
    }
}
