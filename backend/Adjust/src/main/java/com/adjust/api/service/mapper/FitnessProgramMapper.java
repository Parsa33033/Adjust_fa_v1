package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.FitnessProgramDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FitnessProgram} and its DTO {@link FitnessProgramDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FitnessProgramMapper extends EntityMapper<FitnessProgramDTO, FitnessProgram> {


    @Mapping(target = "workouts", ignore = true)
    @Mapping(target = "removeWorkouts", ignore = true)
    @Mapping(target = "program", ignore = true)
    FitnessProgram toEntity(FitnessProgramDTO fitnessProgramDTO);

    default FitnessProgram fromId(Long id) {
        if (id == null) {
            return null;
        }
        FitnessProgram fitnessProgram = new FitnessProgram();
        fitnessProgram.setId(id);
        return fitnessProgram;
    }
}
