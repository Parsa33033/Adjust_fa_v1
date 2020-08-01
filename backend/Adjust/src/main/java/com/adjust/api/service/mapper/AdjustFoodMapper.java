package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustFoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustFood} and its DTO {@link AdjustFoodDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdjustNutritionMapper.class})
public interface AdjustFoodMapper extends EntityMapper<AdjustFoodDTO, AdjustFood> {

    @Mapping(source = "nutrition.id", target = "nutritionId")
    AdjustFoodDTO toDto(AdjustFood adjustFood);

    @Mapping(source = "nutritionId", target = "nutrition")
    AdjustFood toEntity(AdjustFoodDTO adjustFoodDTO);

    default AdjustFood fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustFood adjustFood = new AdjustFood();
        adjustFood.setId(id);
        return adjustFood;
    }
}
