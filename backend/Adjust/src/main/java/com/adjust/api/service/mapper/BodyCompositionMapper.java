package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.BodyCompositionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BodyComposition} and its DTO {@link BodyCompositionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BodyCompositionMapper extends EntityMapper<BodyCompositionDTO, BodyComposition> {


    @Mapping(target = "program", ignore = true)
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
