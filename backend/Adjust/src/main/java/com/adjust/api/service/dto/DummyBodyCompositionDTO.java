package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummyBodyCompositionDTO extends BodyCompositionDTO implements Serializable {

    public DummyBodyCompositionDTO() {}

    public DummyBodyCompositionDTO(BodyCompositionDTO bodyCompositionDTO) {
        this.setId(bodyCompositionDTO.getId());
        this.setHeight(bodyCompositionDTO.getHeight());
        this.setWeight(bodyCompositionDTO.getWeight());
        this.setBmi(bodyCompositionDTO.getBmi());
        this.setCreatedAt(bodyCompositionDTO.getCreatedAt());
        this.setBodyCompositionFile(bodyCompositionDTO.getBodyCompositionFile());
        this.setBodyCompositionFileContentType(bodyCompositionDTO.getBodyCompositionFileContentType());
        this.setBloodTestFile(bodyCompositionDTO.getBloodTestFile());
        this.setBloodTestFileContentType(bodyCompositionDTO.getBloodTestFileContentType());
    }
}
