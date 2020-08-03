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
        this.setAge(bodyCompositionDTO.getAge());
        this.setGender(bodyCompositionDTO.getGender());
        this.setWaist(bodyCompositionDTO.getWaist());
        this.setWrist(bodyCompositionDTO.getWrist());
        this.setMuscleMass(bodyCompositionDTO.getMuscleMass());
        this.setMuscleMassPercentage(bodyCompositionDTO.getMuscleMassPercentage());
        this.setBodyImage(bodyCompositionDTO.getBodyImage());
        this.setBodyImageContentType(bodyCompositionDTO.getBodyImageContentType());
        this.setFatMass(bodyCompositionDTO.getFatMass());
        this.setFatMassPercentage(bodyCompositionDTO.getFatMassPercentage());
    }
}
