package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.BodyCompositionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BodyComposition} and its DTO {@link BodyCompositionDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdjustProgramMapper.class})
public interface BodyCompositionMapper extends EntityMapper<BodyCompositionDTO, BodyComposition> {

    @Mapping(source = "program.id", target = "programId")
    BodyCompositionDTO toDto(BodyComposition bodyComposition);

    @Mapping(source = "programId", target = "program")
    BodyComposition toEntity(BodyCompositionDTO bodyCompositionDTO);

    default BodyComposition fromId(Long id) {
        if (id == null) {
            return null;
        }
        BodyComposition bodyComposition = new BodyComposition();
        bodyComposition.setId(id);
        return bodyComposition;
    }
}
