package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.ProgramDevelopmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProgramDevelopment} and its DTO {@link ProgramDevelopmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdjustProgramMapper.class})
public interface ProgramDevelopmentMapper extends EntityMapper<ProgramDevelopmentDTO, ProgramDevelopment> {

    @Mapping(source = "adjustProgram.id", target = "adjustProgramId")
    ProgramDevelopmentDTO toDto(ProgramDevelopment programDevelopment);

    @Mapping(source = "adjustProgramId", target = "adjustProgram")
    ProgramDevelopment toEntity(ProgramDevelopmentDTO programDevelopmentDTO);

    default ProgramDevelopment fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProgramDevelopment programDevelopment = new ProgramDevelopment();
        programDevelopment.setId(id);
        return programDevelopment;
    }
}
