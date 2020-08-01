package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Nutrition} entity.
 */
public class NutritionDTO implements Serializable {
    
    private Long id;

    private String name;

    private String description;

    private Integer unit;

    private Long adjustNutritionId;

    private Integer caloriesPerUnit;

    private Integer proteinPerUnit;

    private Integer carbsPerUnit;

    private Integer fatInUnit;


    private Long mealId;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Long getAdjustNutritionId() {
        return adjustNutritionId;
    }

    public void setAdjustNutritionId(Long adjustNutritionId) {
        this.adjustNutritionId = adjustNutritionId;
    }

    public Integer getCaloriesPerUnit() {
        return caloriesPerUnit;
    }

    public void setCaloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
    }

    public Integer getProteinPerUnit() {
        return proteinPerUnit;
    }

    public void setProteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
    }

    public Integer getCarbsPerUnit() {
        return carbsPerUnit;
    }

    public void setCarbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
    }

    public Integer getFatInUnit() {
        return fatInUnit;
    }

    public void setFatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutritionDTO)) {
            return false;
        }

        return id != null && id.equals(((NutritionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NutritionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", unit=" + getUnit() +
            ", adjustNutritionId=" + getAdjustNutritionId() +
            ", caloriesPerUnit=" + getCaloriesPerUnit() +
            ", proteinPerUnit=" + getProteinPerUnit() +
            ", carbsPerUnit=" + getCarbsPerUnit() +
            ", fatInUnit=" + getFatInUnit() +
            ", mealId=" + getMealId() +
            "}";
    }
}
