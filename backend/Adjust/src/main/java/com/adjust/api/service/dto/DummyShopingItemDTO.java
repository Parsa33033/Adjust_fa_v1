package com.adjust.api.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustShopingItem} entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DummyShopingItemDTO extends ShopingItemDTO implements Serializable {

    public DummyShopingItemDTO() {}

    public DummyShopingItemDTO(ShopingItemDTO shopingItemDTO) {
        this.setId(shopingItemDTO.getId());
        this.setCartId(shopingItemDTO.getCartId());
        this.setDescription(shopingItemDTO.getDescription());
        this.setName(shopingItemDTO.getName());
        this.setOrderable(shopingItemDTO.isOrderable());
        this.setPrice(shopingItemDTO.getPrice());
        this.setToken(shopingItemDTO.getToken());
        this.setImage(shopingItemDTO.getImage());
        this.setImageContentType(shopingItemDTO.getImageContentType());
    }
}
