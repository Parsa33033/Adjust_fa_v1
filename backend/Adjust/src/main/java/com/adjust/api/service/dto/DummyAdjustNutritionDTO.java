package com.adjust.api.service.dto;

import com.adjust.api.domain.AdjustNutrition;

import java.io.Serializable;
import java.util.List;

public class DummyAdjustNutritionDTO extends AdjustNutritionDTO implements Serializable {

    private List<DummyAdjustFoodDTO> foods;

    public DummyAdjustNutritionDTO() {}

    public DummyAdjustNutritionDTO(AdjustNutritionDTO nutritionDTO) {
        this.setId(nutritionDTO.getId());
        this.setAdjustNutritionId(nutritionDTO.getAdjustNutritionId());
        this.setCaloriesPerUnit(nutritionDTO.getCaloriesPerUnit());
        this.setCarbsPerUnit(nutritionDTO.getCarbsPerUnit());
        this.setDescription(nutritionDTO.getDescription());
        this.setFatInUnit(nutritionDTO.getFatInUnit());
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
