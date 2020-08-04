package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.ProgramDevelopment} entity.
 */
public class ProgramDevelopmentDTO implements Serializable {
    
    private Long id;

    private LocalDate date;

    private Double nutritionScore;

    private Double fitnessScore;


    private Long adjustProgramId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getNutritionScore() {
        return nutritionScore;
    }

    public void setNutritionScore(Double nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public Double getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(Double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public Long getAdjustProgramId() {
        return adjustProgramId;
    }

    public void setAdjustProgramId(Long adjustProgramId) {
        this.adjustProgramId = adjustProgramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgramDevelopmentDTO)) {
            return false;
        }

        return id != null && id.equals(((ProgramDevelopmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProgramDevelopmentDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", nutritionScore=" + getNutritionScore() +
            ", fitnessScore=" + getFitnessScore() +
            ", adjustProgramId=" + getAdjustProgramId() +
            "}";
    }
}
