package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.List;

public class DummyNutritionDTO extends NutritionDTO implements Serializable {

    private List<DummyAdjustFoodDTO> foods;

    public DummyNutritionDTO() {}

    public DummyNutritionDTO(NutritionDTO nutritionDTO) {
        this.setId(nutritionDTO.getId());
        this.setAdjustNutritionId(nutritionDTO.getAdjustNutritionId());
        this.setCaloriesPerUnit(nutritionDTO.getCaloriesPerUnit());
        this.setCarbsPerUnit(nutritionDTO.getCarbsPerUnit());
        this.setDescription(nutritionDTO.getDescription());
        this.setFatInUnit(nutritionDTO.getFatInUnit());
        this.setMealId(nutritionDTO.getMealId());
        this.setName(nutritionDTO.getName());
        this.setProteinPerUnit(nutritionDTO.getProteinPerUnit());
        this.setUnit(nutritionDTO.getUnit());
    }

    public List<DummyAdjustFoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<DummyAdjustFoodDTO> foods) {
        this.foods = foods;
    }
}
