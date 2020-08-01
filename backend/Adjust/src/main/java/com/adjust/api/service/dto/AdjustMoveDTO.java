package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;
import com.adjust.api.domain.enumeration.MuscleType;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustMove} entity.
 */
public class AdjustMoveDTO implements Serializable {
    
    private Long id;

    private String name;

    private String muscleName;

    private MuscleType muscleType;

    private String equipment;

    @Lob
    private byte[] picture;

    private String pictureContentType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public MuscleType getMuscleType() {
        return muscleType;
    }

    public void setMuscleType(MuscleType muscleType) {
        this.muscleType = muscleType;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustMoveDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjustMoveDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustMoveDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", muscleName='" + getMuscleName() + "'" +
            ", muscleType='" + getMuscleType() + "'" +
            ", equipment='" + getEquipment() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
