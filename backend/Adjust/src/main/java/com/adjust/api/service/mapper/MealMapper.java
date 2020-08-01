package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.MealDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Meal} and its DTO {@link MealDTO}.
 */
@Mapper(componentModel = "spring", uses = {NutritionProgramMapper.class})
public interface MealMapper extends EntityMapper<MealDTO, Meal> {

    @Mapping(source = "nutritionProgram.id", target = "nutritionProgramId")
    MealDTO toDto(Meal meal);

    @Mapping(target = "nutritions", ignore = true)
    @Mapping(target = "removeNutritions", ignore = true)
    @Mapping(source = "nutritionProgramId", target = "nutritionProgram")
    Meal toEntity(MealDTO mealDTO);

    default Meal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Meal meal = new Meal();
        meal.setId(id);
        return meal;
    }
}
