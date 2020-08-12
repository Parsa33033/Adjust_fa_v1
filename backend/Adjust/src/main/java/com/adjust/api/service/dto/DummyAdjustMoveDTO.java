package com.adjust.api.service.dto;

public class DummyAdjustMoveDTO extends AdjustMoveDTO{
    public DummyAdjustMoveDTO() {}

    public DummyAdjustMoveDTO(AdjustMoveDTO moveDTO) {
        this.setId(moveDTO.getId());
        this.setEquipment(moveDTO.getEquipment());
        this.setMuscleName(moveDTO.getMuscleName());
        this.setMuscleType(moveDTO.getMuscleType());
        this.setName(moveDTO.getName());
        this.setPicture(moveDTO.getPicture());
        this.setPictureContentType(moveDTO.getPictureContentType());
    }
}
