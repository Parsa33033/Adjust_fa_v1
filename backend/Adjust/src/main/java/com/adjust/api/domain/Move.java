package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.adjust.api.domain.enumeration.MuscleType;

/**
 * A Move.
 */
@Entity
@Table(name = "move")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Move implements Serializable {

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

    @Column(name = "adjust_move_id")
    private Long adjustMoveId;

    @OneToOne(mappedBy = "move")
    @JsonIgnore
    private Exercise exercise;

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

    public Move name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public Move muscleName(String muscleName) {
        this.muscleName = muscleName;
        return this;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public MuscleType getMuscleType() {
        return muscleType;
    }

    public Move muscleType(MuscleType muscleType) {
        this.muscleType = muscleType;
        return this;
    }

    public void setMuscleType(MuscleType muscleType) {
        this.muscleType = muscleType;
    }

    public String getEquipment() {
        return equipment;
    }

    public Move equipment(String equipment) {
        this.equipment = equipment;
        return this;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public byte[] getPicture() {
        return picture;
    }

    public Move picture(byte[] picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public Move pictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
        return this;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public Long getAdjustMoveId() {
        return adjustMoveId;
    }

    public Move adjustMoveId(Long adjustMoveId) {
        this.adjustMoveId = adjustMoveId;
        return this;
    }

    public void setAdjustMoveId(Long adjustMoveId) {
        this.adjustMoveId = adjustMoveId;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public Move exercise(Exercise exercise) {
        this.exercise = exercise;
        return this;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Move)) {
            return false;
        }
        return id != null && id.equals(((Move) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Move{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", muscleName='" + getMuscleName() + "'" +
            ", muscleType='" + getMuscleType() + "'" +
            ", equipment='" + getEquipment() + "'" +
            ", picture='" + getPicture() + "'" +
            ", pictureContentType='" + getPictureContentType() + "'" +
            ", adjustMoveId=" + getAdjustMoveId() +
            "}";
    }
}
