package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummyAdjustProgramDTO extends AdjustProgramDTO implements Serializable {

    DummySpecialistDTO specialist;
    DummyAdjustClientDTO client;

    DummyBodyCompositionDTO bodyComposition;
    DummyNutritionProgramDTO nutritionProgram;
    DummyFitnessProgramDTO fitnessProgram;

    public DummyAdjustProgramDTO() {}

    public DummyAdjustProgramDTO(AdjustProgramDTO adjustProgramDTO) {
        this.setId(adjustProgramDTO.getId());
        this.setClientId(adjustProgramDTO.getClientId());
        this.setNutritionProgramId(adjustProgramDTO.getNutritionProgramId());
        this.setFitnessProgramId(adjustProgramDTO.getNutritionProgramId());
        this.setBodyCompostionId(adjustProgramDTO.getBodyCompostionId());
        this.setCreatedAt(adjustProgramDTO.getCreatedAt());
        this.setDesigned(adjustProgramDTO.isDesigned());
        this.setDone(adjustProgramDTO.isDone());
        this.setPaid(adjustProgramDTO.isPaid());
        this.setExpirationDate(adjustProgramDTO.getExpirationDate());
        this.setSpecialistId(adjustProgramDTO.getSpecialistId());
    }

    public DummySpecialistDTO getSpecialist() {
        return specialist;
    }

    public void setSpecialist(DummySpecialistDTO specialist) {
        this.specialist = specialist;
    }

    public DummyAdjustClientDTO getClient() {
        return client;
    }

    public void setClient(DummyAdjustClientDTO client) {
        this.client = client;
    }

    public DummyBodyCompositionDTO getBodyComposition() {
        return bodyComposition;
    }

    public void setBodyComposition(DummyBodyCompositionDTO bodyComposition) {
        this.bodyComposition = bodyComposition;
    }

    public DummyNutritionProgramDTO getNutritionProgram() {
        return nutritionProgram;
    }

    public void setNutritionProgram(DummyNutritionProgramDTO nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
    }

    public DummyFitnessProgramDTO getFitnessProgram() {
        return fitnessProgram;
    }

    public void setFitnessProgram(DummyFitnessProgramDTO fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
    }
}
