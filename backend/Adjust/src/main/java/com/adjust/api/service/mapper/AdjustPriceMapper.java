package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustPriceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustPrice} and its DTO {@link AdjustPriceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustPriceMapper extends EntityMapper<AdjustPriceDTO, AdjustPrice> {



    default AdjustPrice fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustPrice adjustPrice = new AdjustPrice();
        adjustPrice.setId(id);
        return adjustPrice;
    }
}
