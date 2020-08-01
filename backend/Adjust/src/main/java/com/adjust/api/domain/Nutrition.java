package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Nutrition.
 */
@Entity
@Table(name = "nutrition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nutrition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit")
    private Integer unit;

    @Column(name = "adjust_nutrition_id")
    private Long adjustNutritionId;

    @Column(name = "calories_per_unit")
    private Integer caloriesPerUnit;

    @Column(name = "protein_per_unit")
    private Integer proteinPerUnit;

    @Column(name = "carbs_per_unit")
    private Integer carbsPerUnit;

    @Column(name = "fat_in_unit")
    private Integer fatInUnit;

    @ManyToOne
    @JsonIgnoreProperties(value = "nutritions", allowSetters = true)
    private Meal meal;

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

    public Nutrition name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Nutrition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnit() {
        return unit;
    }

    public Nutrition unit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Long getAdjustNutritionId() {
        return adjustNutritionId;
    }

    public Nutrition adjustNutritionId(Long adjustNutritionId) {
        this.adjustNutritionId = adjustNutritionId;
        return this;
    }

    public void setAdjustNutritionId(Long adjustNutritionId) {
        this.adjustNutritionId = adjustNutritionId;
    }

    public Integer getCaloriesPerUnit() {
        return caloriesPerUnit;
    }

    public Nutrition caloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
        return this;
    }

    public void setCaloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
    }

    public Integer getProteinPerUnit() {
        return proteinPerUnit;
    }

    public Nutrition proteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
        return this;
    }

    public void setProteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
    }

    public Integer getCarbsPerUnit() {
        return carbsPerUnit;
    }

    public Nutrition carbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
        return this;
    }

    public void setCarbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
    }

    public Integer getFatInUnit() {
        return fatInUnit;
    }

    public Nutrition fatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
        return this;
    }

    public void setFatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
    }

    public Meal getMeal() {
        return meal;
    }

    public Nutrition meal(Meal meal) {
        this.meal = meal;
        return this;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nutrition)) {
            return false;
        }
        return id != null && id.equals(((Nutrition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Nutrition{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", unit=" + getUnit() +
            ", adjustNutritionId=" + getAdjustNutritionId() +
            ", caloriesPerUnit=" + getCaloriesPerUnit() +
            ", proteinPerUnit=" + getProteinPerUnit() +
            ", carbsPerUnit=" + getCarbsPerUnit() +
            ", fatInUnit=" + getFatInUnit() +
            "}";
    }
}
