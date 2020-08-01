package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.NutritionProgram} entity.
 */
public class NutritionProgramDTO implements Serializable {
    
    private Long id;

    private Double dailyCalories;

    private Integer proteinPercentage;

    private Integer carbsPercentage;

    private Integer fatPercentage;

    @Lob
    private String description;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(Double dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public Integer getProteinPercentage() {
        return proteinPercentage;
    }

    public void setProteinPercentage(Integer proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public Integer getCarbsPercentage() {
        return carbsPercentage;
    }

    public void setCarbsPercentage(Integer carbsPercentage) {
        this.carbsPercentage = carbsPercentage;
    }

    public Integer getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(Integer fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutritionProgramDTO)) {
            return false;
        }

        return id != null && id.equals(((NutritionProgramDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NutritionProgramDTO{" +
            "id=" + getId() +
            ", dailyCalories=" + getDailyCalories() +
            ", proteinPercentage=" + getProteinPercentage() +
            ", carbsPercentage=" + getCarbsPercentage() +
            ", fatPercentage=" + getFatPercentage() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
