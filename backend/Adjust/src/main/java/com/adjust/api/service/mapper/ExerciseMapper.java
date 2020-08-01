package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.ExerciseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Exercise} and its DTO {@link ExerciseDTO}.
 */
@Mapper(componentModel = "spring", uses = {MoveMapper.class, WorkoutMapper.class})
public interface ExerciseMapper extends EntityMapper<ExerciseDTO, Exercise> {

    @Mapping(source = "move.id", target = "moveId")
    @Mapping(source = "workout.id", target = "workoutId")
    ExerciseDTO toDto(Exercise exercise);

    @Mapping(source = "moveId", target = "move")
    @Mapping(source = "workoutId", target = "workout")
    Exercise toEntity(ExerciseDTO exerciseDTO);

    default Exercise fromId(Long id) {
        if (id == null) {
            return null;
        }
        Exercise exercise = new Exercise();
        exercise.setId(id);
        return exercise;
    }
}
