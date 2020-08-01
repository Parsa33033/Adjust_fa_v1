package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummyAdjustFoodDTO extends AdjustFoodDTO implements Serializable {

    public DummyAdjustFoodDTO() {}

    public DummyAdjustFoodDTO(AdjustFoodDTO adjustFoodDTO) {
        this.setDescription(adjustFoodDTO.getDescription());
        this.setId(adjustFoodDTO.getId());
        this.setName(adjustFoodDTO.getName());
        this.setNutritionId(adjustFoodDTO.getNutritionId());
    }
}
