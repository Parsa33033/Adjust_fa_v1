package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.SpecialistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Specialist} and its DTO {@link SpecialistDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SpecialistMapper extends EntityMapper<SpecialistDTO, Specialist> {


    @Mapping(target = "programs", ignore = true)
    @Mapping(target = "removePrograms", ignore = true)
    @Mapping(target = "conversation", ignore = true)
    Specialist toEntity(SpecialistDTO specialistDTO);

    default Specialist fromId(Long id) {
        if (id == null) {
            return null;
        }
        Specialist specialist = new Specialist();
        specialist.setId(id);
        return specialist;
    }
}
