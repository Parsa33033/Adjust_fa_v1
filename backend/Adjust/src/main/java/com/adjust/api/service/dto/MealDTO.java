package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Meal} entity.
 */
public class MealDTO implements Serializable {
    
    private Long id;

    private String name;

    private Integer number;


    private Long nutritionProgramId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getNutritionProgramId() {
        return nutritionProgramId;
    }

    public void setNutritionProgramId(Long nutritionProgramId) {
        this.nutritionProgramId = nutritionProgramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MealDTO)) {
            return false;
        }

        return id != null && id.equals(((MealDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MealDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number=" + getNumber() +
            ", nutritionProgramId=" + getNutritionProgramId() +
            "}";
    }
}
