package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustShopingItem} entity.
 */
public class AdjustShopingItemDTO implements Serializable {
    
    private Long id;

    private String name;

    private String description;

    private Double token;

    private Double price;

    @Lob
    private byte[] image;

    private String imageContentType;
    private Boolean orderable;

    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustShopingItemDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjustShopingItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustShopingItemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", token=" + getToken() +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", orderable='" + isOrderable() + "'" +
            "}";
    }
}
