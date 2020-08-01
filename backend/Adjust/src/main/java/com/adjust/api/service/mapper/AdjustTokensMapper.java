package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustTokensDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustTokens} and its DTO {@link AdjustTokensDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustTokensMapper extends EntityMapper<AdjustTokensDTO, AdjustTokens> {



    default AdjustTokens fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustTokens adjustTokens = new AdjustTokens();
        adjustTokens.setId(id);
        return adjustTokens;
    }
}
