package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustNutritionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustNutrition} and its DTO {@link AdjustNutritionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustNutritionMapper extends EntityMapper<AdjustNutritionDTO, AdjustNutrition> {


    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "removeFoods", ignore = true)
    AdjustNutrition toEntity(AdjustNutritionDTO adjustNutritionDTO);

    default AdjustNutrition fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustNutrition adjustNutrition = new AdjustNutrition();
        adjustNutrition.setId(id);
        return adjustNutrition;
    }
}
