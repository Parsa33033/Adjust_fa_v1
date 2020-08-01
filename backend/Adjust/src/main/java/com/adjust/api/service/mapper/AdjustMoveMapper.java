package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustMoveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustMove} and its DTO {@link AdjustMoveDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustMoveMapper extends EntityMapper<AdjustMoveDTO, AdjustMove> {



    default AdjustMove fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustMove adjustMove = new AdjustMove();
        adjustMove.setId(id);
        return adjustMove;
    }
}
