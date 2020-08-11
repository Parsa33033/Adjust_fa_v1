package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.List;

public class DummyAdjustProgramDTO extends AdjustProgramDTO implements Serializable {

    DummySpecialistDTO specialist;
    DummyAdjustClientDTO client;

    List<DummyProgramDevelopmentDTO> programDevelopments;
    List<DummyBodyCompositionDTO> bodyCompositions;
    DummyNutritionProgramDTO nutritionProgram;
    DummyFitnessProgramDTO fitnessProgram;

    public DummyAdjustProgramDTO() {}

    public DummyAdjustProgramDTO(AdjustProgramDTO adjustProgramDTO) {
        this.setId(adjustProgramDTO.getId());
        this.setClientId(adjustProgramDTO.getClientId());
        this.setNutritionProgramId(adjustProgramDTO.getNutritionProgramId());
        this.setFitnessProgramId(adjustProgramDTO.getNutritionProgramId());
        this.setCreatedAt(adjustProgramDTO.getCreatedAt());
        this.setDesigned(adjustProgramDTO.isDesigned());
        this.setNutritionDone(adjustProgramDTO.isNutritionDone());
        this.setFitnessDone(adjustProgramDTO.isFitnessDone());
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

    public List<DummyProgramDevelopmentDTO> getProgramDevelopments() {
        return programDevelopments;
    }

    public void setProgramDevelopments(List<DummyProgramDevelopmentDTO> programDevelopments) {
        this.programDevelopments = programDevelopments;
    }

    public List<DummyBodyCompositionDTO> getBodyCompositions() {
        return bodyCompositions;
    }

    public void setBodyCompositions(List<DummyBodyCompositionDTO> bodyCompositions) {
        this.bodyCompositions = bodyCompositions;
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
