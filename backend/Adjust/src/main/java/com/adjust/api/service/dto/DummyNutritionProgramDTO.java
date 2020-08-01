package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.List;

public class DummyNutritionProgramDTO extends NutritionProgramDTO implements Serializable {
    List<DummyMealDTO> meals;

    public DummyNutritionProgramDTO() {}

    public DummyNutritionProgramDTO(NutritionProgramDTO nutritionProgramDTO) {
        this.setId(nutritionProgramDTO.getId());
        this.setCarbsPercentage(nutritionProgramDTO.getCarbsPercentage());
        this.setDailyCalories(nutritionProgramDTO.getDailyCalories());
        this.setFatPercentage(nutritionProgramDTO.getFatPercentage());
        this.setProteinPercentage(nutritionProgramDTO.getProteinPercentage());
        this.setDescription(nutritionProgramDTO.getDescription());
    }

    public List<DummyMealDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<DummyMealDTO> meals) {
        this.meals = meals;
    }
}
