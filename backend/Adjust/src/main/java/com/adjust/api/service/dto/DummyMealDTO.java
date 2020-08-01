package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.List;

public class DummyMealDTO extends MealDTO implements Serializable {

    private List<DummyNutritionDTO> nutritions;

    public DummyMealDTO() {}

    public DummyMealDTO(MealDTO mealDTO) {
        this.setId(mealDTO.getId());
        this.setNutritionProgramId(mealDTO.getNutritionProgramId());
        this.setName(mealDTO.getName());
        this.setNumber(mealDTO.getNumber());

    }

    public List<DummyNutritionDTO> getNutritions() {
        return nutritions;
    }

    public void setNutritions(List<DummyNutritionDTO> nutritions) {
        this.nutritions = nutritions;
    }
}
