package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustProgramDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustProgram} and its DTO {@link AdjustProgramDTO}.
 */
@Mapper(componentModel = "spring", uses = {FitnessProgramMapper.class, NutritionProgramMapper.class, AdjustClientMapper.class, SpecialistMapper.class})
public interface AdjustProgramMapper extends EntityMapper<AdjustProgramDTO, AdjustProgram> {

    @Mapping(source = "fitnessProgram.id", target = "fitnessProgramId")
    @Mapping(source = "nutritionProgram.id", target = "nutritionProgramId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "specialist.id", target = "specialistId")
    AdjustProgramDTO toDto(AdjustProgram adjustProgram);

    @Mapping(source = "fitnessProgramId", target = "fitnessProgram")
    @Mapping(source = "nutritionProgramId", target = "nutritionProgram")
    @Mapping(target = "programDevelopments", ignore = true)
    @Mapping(target = "removeProgramDevelopments", ignore = true)
    @Mapping(target = "bodyCompostions", ignore = true)
    @Mapping(target = "removeBodyCompostions", ignore = true)
    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "specialistId", target = "specialist")
    AdjustProgram toEntity(AdjustProgramDTO adjustProgramDTO);

    default AdjustProgram fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustProgram adjustProgram = new AdjustProgram();
        adjustProgram.setId(id);
        return adjustProgram;
    }
}
