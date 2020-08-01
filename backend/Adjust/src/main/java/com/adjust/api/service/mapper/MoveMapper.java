package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.MoveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Move} and its DTO {@link MoveDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MoveMapper extends EntityMapper<MoveDTO, Move> {


    @Mapping(target = "exercise", ignore = true)
    Move toEntity(MoveDTO moveDTO);

    default Move fromId(Long id) {
        if (id == null) {
            return null;
        }
        Move move = new Move();
        move.setId(id);
        return move;
    }
}
