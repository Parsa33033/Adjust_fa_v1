package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.NutritionProgramDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NutritionProgram} and its DTO {@link NutritionProgramDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NutritionProgramMapper extends EntityMapper<NutritionProgramDTO, NutritionProgram> {


    @Mapping(target = "meals", ignore = true)
    @Mapping(target = "removeMeals", ignore = true)
    @Mapping(target = "program", ignore = true)
    NutritionProgram toEntity(NutritionProgramDTO nutritionProgramDTO);

    default NutritionProgram fromId(Long id) {
        if (id == null) {
            return null;
        }
        NutritionProgram nutritionProgram = new NutritionProgram();
        nutritionProgram.setId(id);
        return nutritionProgram;
    }
}
