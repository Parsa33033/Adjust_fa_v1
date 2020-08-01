package com.adjust.api.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.adjust.api.domain.Cart} entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DummyCartDTO extends CartDTO implements Serializable {


    private List<DummyShopingItemDTO> items;

    public DummyCartDTO() {}

    public DummyCartDTO(CartDTO cartDTO) {
        this.setId(cartDTO.getId());
        this.setFirstName(cartDTO.getFirstName());
        this.setLastName(cartDTO.getLastName());
        this.setUsername(cartDTO.getUsername());
    }

    public List<DummyShopingItemDTO> getItems() {
        return items;
    }

    public void setItems(List<DummyShopingItemDTO> items) {
        this.items = items;
    }
}
