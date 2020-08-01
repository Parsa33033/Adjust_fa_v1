package com.adjust.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AdjustPrice.
 */
@Entity
@Table(name = "adjust_price")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjustPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private Double token;

    @Column(name = "price")
    private Double price;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AdjustPrice name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getToken() {
        return token;
    }

    public AdjustPrice token(Double token) {
        this.token = token;
        return this;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public Double getPrice() {
        return price;
    }

    public AdjustPrice price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustPrice)) {
            return false;
        }
        return id != null && id.equals(((AdjustPrice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustPrice{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", token=" + getToken() +
            ", price=" + getPrice() +
            "}";
    }
}
