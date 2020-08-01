package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.List;

public class DummyWorkoutDTO extends WorkoutDTO implements Serializable {
    List<DummyExerciseDTO> exercises;

    public DummyWorkoutDTO() {}

    public DummyWorkoutDTO(WorkoutDTO workoutDTO) {
        this.setId(workoutDTO.getId());
        this.setDayNumber(workoutDTO.getDayNumber());
        this.setProgramId(workoutDTO.getProgramId());
    }

    public List<DummyExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<DummyExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}
