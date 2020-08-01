package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummyAdjustClientDTO extends AdjustClientDTO implements Serializable {

    public DummyAdjustClientDTO() {}

    public DummyAdjustClientDTO(AdjustClientDTO adjustClientDTO) {
        this.setId(adjustClientDTO.getId());
        this.setUsername(adjustClientDTO.getUsername());
        this.setFirstName(adjustClientDTO.getFirstName());
        this.setLastName(adjustClientDTO.getLastName());
        this.setGender(adjustClientDTO.getGender());
        this.setBirthDate(adjustClientDTO.getBirthDate());
        this.setAge(adjustClientDTO.getAge());
        this.setToken(adjustClientDTO.getToken());
        this.setScore(adjustClientDTO.getScore());
        this.setImage(adjustClientDTO.getImage());
        this.setImageContentType(adjustClientDTO.getImageContentType());
    }
}
