package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.NutritionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Nutrition} and its DTO {@link NutritionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MealMapper.class})
public interface NutritionMapper extends EntityMapper<NutritionDTO, Nutrition> {

    @Mapping(source = "meal.id", target = "mealId")
    NutritionDTO toDto(Nutrition nutrition);

    @Mapping(source = "mealId", target = "meal")
    Nutrition toEntity(NutritionDTO nutritionDTO);

    default Nutrition fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nutrition nutrition = new Nutrition();
        nutrition.setId(id);
        return nutrition;
    }
}
