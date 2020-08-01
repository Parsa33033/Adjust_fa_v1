package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Exercise} entity.
 */
public class ExerciseDTO implements Serializable {
    
    private Long id;

    private Integer number;

    private Integer sets;

    private Integer repsMin;

    private Integer repsMax;


    private Long moveId;

    private Long workoutId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getRepsMin() {
        return repsMin;
    }

    public void setRepsMin(Integer repsMin) {
        this.repsMin = repsMin;
    }

    public Integer getRepsMax() {
        return repsMax;
    }

    public void setRepsMax(Integer repsMax) {
        this.repsMax = repsMax;
    }

    public Long getMoveId() {
        return moveId;
    }

    public void setMoveId(Long moveId) {
        this.moveId = moveId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExerciseDTO)) {
            return false;
        }

        return id != null && id.equals(((ExerciseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExerciseDTO{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", sets=" + getSets() +
            ", repsMin=" + getRepsMin() +
            ", repsMax=" + getRepsMax() +
            ", moveId=" + getMoveId() +
            ", workoutId=" + getWorkoutId() +
            "}";
    }
}
