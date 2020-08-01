package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.ShopingItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShopingItem} and its DTO {@link ShopingItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {CartMapper.class})
public interface ShopingItemMapper extends EntityMapper<ShopingItemDTO, ShopingItem> {

    @Mapping(source = "cart.id", target = "cartId")
    ShopingItemDTO toDto(ShopingItem shopingItem);

    @Mapping(source = "cartId", target = "cart")
    ShopingItem toEntity(ShopingItemDTO shopingItemDTO);

    default ShopingItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShopingItem shopingItem = new ShopingItem();
        shopingItem.setId(id);
        return shopingItem;
    }
}
