package com.adjust.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.adjust.api.domain.enumeration.MuscleType;

/**
 * A AdjustMove.
 */
@Entity
@Table(name = "adjust_move")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjustMove implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "muscle_name")
    private String muscleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_type")
    private MuscleType muscleType;

    @Column(name = "equipment")
    private String equipment;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "picture_content_type")
    private String pictureContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AdjustMove name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public AdjustMove muscleName(String muscleName) {
        this.muscleName = muscleName;
        return this;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public MuscleType getMuscleType() {
        return muscleType;
    }

    public AdjustMove muscleType(MuscleType muscleType) {
        this.muscleType = muscleType;
        return this;
    }

    public void setMuscleType(MuscleType muscleType) {
        this.muscleType = muscleType;
    }

    public String getEquipment() {
        return equipment;
    }

    public AdjustMove equipment(String equipment) {
        this.equipment = equipment;
        return this;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public byte[] getPicture() {
        return picture;
    }

    public AdjustMove picture(byte[] picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public AdjustMove pictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
        return this;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustMove)) {
            return false;
        }
        return id != null && id.equals(((AdjustMove) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustMove{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", muscleName='" + getMuscleName() + "'" +
            ", muscleType='" + getMuscleType() + "'" +
            ", equipment='" + getEquipment() + "'" +
            ", picture='" + getPicture() + "'" +
            ", pictureContentType='" + getPictureContentType() + "'" +
            "}";
    }
}
