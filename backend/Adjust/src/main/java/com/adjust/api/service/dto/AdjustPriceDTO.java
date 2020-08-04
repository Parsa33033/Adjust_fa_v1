package com.adjust.api.service.dto;

import java.io.Serializable;
import com.adjust.api.domain.enumeration.PurchaseOption;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustPrice} entity.
 */
public class AdjustPriceDTO implements Serializable {
    
    private Long id;

    private String name;

    private PurchaseOption option;

    private Double token;

    private Double price;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PurchaseOption getOption() {
        return option;
    }

    public void setOption(PurchaseOption option) {
        this.option = option;
    }

    public Double getToken() {
        return token;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustPriceDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjustPriceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustPriceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", option='" + getOption() + "'" +
            ", token=" + getToken() +
            ", price=" + getPrice() +
            "}";
    }
}
