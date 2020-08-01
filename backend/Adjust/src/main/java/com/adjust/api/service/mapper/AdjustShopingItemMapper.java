package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustShopingItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustShopingItem} and its DTO {@link AdjustShopingItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustShopingItemMapper extends EntityMapper<AdjustShopingItemDTO, AdjustShopingItem> {



    default AdjustShopingItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustShopingItem adjustShopingItem = new AdjustShopingItem();
        adjustShopingItem.setId(id);
        return adjustShopingItem;
    }
}
