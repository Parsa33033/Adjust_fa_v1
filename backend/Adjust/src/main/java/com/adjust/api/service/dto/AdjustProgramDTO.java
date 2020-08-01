package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustProgram} entity.
 */
public class AdjustProgramDTO implements Serializable {
    
    private Long id;

    private LocalDate createdAt;

    private LocalDate expirationDate;

    private Boolean designed;

    private Boolean done;

    private Boolean paid;


    private Long bodyCompostionId;

    private Long fitnessProgramId;

    private Long nutritionProgramId;

    private Long clientId;

    private Long specialistId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean isDesigned() {
        return designed;
    }

    public void setDesigned(Boolean designed) {
        this.designed = designed;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean isPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Long getBodyCompostionId() {
        return bodyCompostionId;
    }

    public void setBodyCompostionId(Long bodyCompositionId) {
        this.bodyCompostionId = bodyCompositionId;
    }

    public Long getFitnessProgramId() {
        return fitnessProgramId;
    }

    public void setFitnessProgramId(Long fitnessProgramId) {
        this.fitnessProgramId = fitnessProgramId;
    }

    public Long getNutritionProgramId() {
        return nutritionProgramId;
    }

    public void setNutritionProgramId(Long nutritionProgramId) {
        this.nutritionProgramId = nutritionProgramId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long adjustClientId) {
        this.clientId = adjustClientId;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustProgramDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjustProgramDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustProgramDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", designed='" + isDesigned() + "'" +
            ", done='" + isDone() + "'" +
            ", paid='" + isPaid() + "'" +
            ", bodyCompostionId=" + getBodyCompostionId() +
            ", fitnessProgramId=" + getFitnessProgramId() +
            ", nutritionProgramId=" + getNutritionProgramId() +
            ", clientId=" + getClientId() +
            ", specialistId=" + getSpecialistId() +
            "}";
    }
}
