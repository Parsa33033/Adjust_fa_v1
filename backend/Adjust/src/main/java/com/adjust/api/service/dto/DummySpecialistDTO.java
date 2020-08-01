package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummySpecialistDTO extends SpecialistDTO implements Serializable {
    public DummySpecialistDTO() {}

    public DummySpecialistDTO(SpecialistDTO specialistDTO) {
        this.setId(specialistDTO.getId());
        this.setBirth(specialistDTO.getBirth());
        this.setBusy(specialistDTO.isBusy());
        this.setDegree(specialistDTO.getDegree());
        this.setField(specialistDTO.getField());
        this.setFirstName(specialistDTO.getFirstName());
        this.setLastName(specialistDTO.getLastName());
        this.setResume(specialistDTO.getResume());
        this.setStars(specialistDTO.getStars());
        this.setUsername(specialistDTO.getUsername());
        this.setImage(specialistDTO.getImage());
        this.setImageContentType(specialistDTO.getImageContentType());
        this.setGender(specialistDTO.getGender());
    }
}
