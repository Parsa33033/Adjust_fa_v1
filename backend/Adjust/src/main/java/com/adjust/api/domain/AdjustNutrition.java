package com.adjust.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AdjustNutrition.
 */
@Entity
@Table(name = "adjust_nutrition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjustNutrition implements Serializable {

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

    @OneToMany(mappedBy = "nutrition", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AdjustFood> foods = new HashSet<>();

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

    public AdjustNutrition name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public AdjustNutrition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnit() {
        return unit;
    }

    public AdjustNutrition unit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Long getAdjustNutritionId() {
        return adjustNutritionId;
    }

    public AdjustNutrition adjustNutritionId(Long adjustNutritionId) {
        this.adjustNutritionId = adjustNutritionId;
        return this;
    }

    public void setAdjustNutritionId(Long adjustNutritionId) {
        this.adjustNutritionId = adjustNutritionId;
    }

    public Integer getCaloriesPerUnit() {
        return caloriesPerUnit;
    }

    public AdjustNutrition caloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
        return this;
    }

    public void setCaloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
    }

    public Integer getProteinPerUnit() {
        return proteinPerUnit;
    }

    public AdjustNutrition proteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
        return this;
    }

    public void setProteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
    }

    public Integer getCarbsPerUnit() {
        return carbsPerUnit;
    }

    public AdjustNutrition carbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
        return this;
    }

    public void setCarbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
    }

    public Integer getFatInUnit() {
        return fatInUnit;
    }

    public AdjustNutrition fatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
        return this;
    }

    public void setFatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
    }

    public Set<AdjustFood> getFoods() {
        return foods;
    }

    public AdjustNutrition foods(Set<AdjustFood> adjustFoods) {
        this.foods = adjustFoods;
        return this;
    }

    public AdjustNutrition addFoods(AdjustFood adjustFood) {
        this.foods.add(adjustFood);
        adjustFood.setNutrition(this);
        return this;
    }

    public AdjustNutrition removeFoods(AdjustFood adjustFood) {
        this.foods.remove(adjustFood);
        adjustFood.setNutrition(null);
        return this;
    }

    public void setFoods(Set<AdjustFood> adjustFoods) {
        this.foods = adjustFoods;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustNutrition)) {
            return false;
        }
        return id != null && id.equals(((AdjustNutrition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustNutrition{" +
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
