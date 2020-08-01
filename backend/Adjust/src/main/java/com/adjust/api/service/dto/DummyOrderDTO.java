package com.adjust.api.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Order} entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DummyOrderDTO extends OrderDTO implements Serializable {


    private DummyCartDTO cart;

    public DummyOrderDTO() {};

    public DummyOrderDTO(OrderDTO orderDTO) {
        this.setId(orderDTO.getId());
        this.setCartId(orderDTO.getCartId());
        this.setAddress1(orderDTO.getAddress1());
        this.setAddress2(orderDTO.getAddress2());
        this.setCity(orderDTO.getCity());
        this.setCountry(orderDTO.getCountry());
        this.setDone(orderDTO.isDone());
        this.setUsername(orderDTO.getUsername());
        this.setFirstName(orderDTO.getFirstName());
        this.setLastName(orderDTO.getLastName());
        this.setPhoneNumber(orderDTO.getPhoneNumber());
        this.setEmail(orderDTO.getEmail());
        this.setState(orderDTO.getState());
        this.setReceived(orderDTO.isReceived());
    }

    public DummyCartDTO getCart() {
        return cart;
    }

    public void setCart(DummyCartDTO cart) {
        this.cart = cart;
    }
}
