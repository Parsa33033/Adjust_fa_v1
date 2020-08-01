package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummyMoveDTO extends MoveDTO implements Serializable {

    public DummyMoveDTO() {}

    public DummyMoveDTO(MoveDTO moveDTO) {
        this.setId(moveDTO.getId());
        this.setAdjustMoveId(moveDTO.getAdjustMoveId());
        this.setEquipment(moveDTO.getEquipment());
        this.setMuscleName(moveDTO.getMuscleName());
        this.setMuscleType(moveDTO.getMuscleType());
        this.setName(moveDTO.getName());
        this.setPicture(moveDTO.getPicture());
        this.setPictureContentType(moveDTO.getPictureContentType());
    }
}
