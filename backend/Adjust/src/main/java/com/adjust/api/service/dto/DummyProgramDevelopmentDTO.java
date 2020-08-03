package com.adjust.api.service.dto;

import java.io.Serializable;

public class DummyProgramDevelopmentDTO extends ProgramDevelopmentDTO implements Serializable {

    public DummyProgramDevelopmentDTO() {}

    public DummyProgramDevelopmentDTO(ProgramDevelopmentDTO programDevelopmentDTO) {
        this.setId(programDevelopmentDTO.getId());
        this.setAdjustProgramId(programDevelopmentDTO.getAdjustProgramId());
        this.setDate(programDevelopmentDTO.getDate());
        this.setFitnessScore(programDevelopmentDTO.getFitnessScore());
        this.setWorkoutScore(programDevelopmentDTO.getWorkoutScore());
    }
}
