package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.List;

public class DummyFitnessProgramDTO extends FitnessProgramDTO implements Serializable {
    List<DummyWorkoutDTO> workouts;

    public DummyFitnessProgramDTO() {}

    public DummyFitnessProgramDTO(FitnessProgramDTO fitnessProgramDTO) {
        this.setId(fitnessProgramDTO.getId());
        this.setType(fitnessProgramDTO.getType());
        this.setDescription(fitnessProgramDTO.getDescription());
    }

    public List<DummyWorkoutDTO> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<DummyWorkoutDTO> workouts) {
        this.workouts = workouts;
    }
}
