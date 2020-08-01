package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummyExerciseDTO extends ExerciseDTO implements Serializable {
    DummyMoveDTO move;

    public DummyExerciseDTO() {}

    public DummyExerciseDTO(ExerciseDTO exerciseDTO) {
        this.setId(exerciseDTO.getId());
        this.setNumber(exerciseDTO.getNumber());
        this.setRepsMax(exerciseDTO.getRepsMax());
        this.setRepsMin(exerciseDTO.getRepsMin());
        this.setSets(exerciseDTO.getSets());
        this.setWorkoutId(exerciseDTO.getWorkoutId());
        this.setMoveId(exerciseDTO.getMoveId());
    }

    public DummyMoveDTO getMove() {
        return move;
    }

    public void setMove(DummyMoveDTO move) {
        this.move = move;
    }
}
