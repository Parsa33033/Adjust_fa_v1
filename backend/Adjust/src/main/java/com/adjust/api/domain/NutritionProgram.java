package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NutritionProgram.
 */
@Entity
@Table(name = "nutrition_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NutritionProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "daily_calories")
    private Double dailyCalories;

    @Column(name = "protein_percentage")
    private Integer proteinPercentage;

    @Column(name = "carbs_percentage")
    private Integer carbsPercentage;

    @Column(name = "fat_percentage")
    private Integer fatPercentage;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "nutritionProgram", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Meal> meals = new HashSet<>();

    @OneToOne(mappedBy = "nutritionProgram")
    @JsonIgnore
    private AdjustProgram program;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDailyCalories() {
        return dailyCalories;
    }

    public NutritionProgram dailyCalories(Double dailyCalories) {
        this.dailyCalories = dailyCalories;
        return this;
    }

    public void setDailyCalories(Double dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public Integer getProteinPercentage() {
        return proteinPercentage;
    }

    public NutritionProgram proteinPercentage(Integer proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
        return this;
    }

    public void setProteinPercentage(Integer proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public Integer getCarbsPercentage() {
        return carbsPercentage;
    }

    public NutritionProgram carbsPercentage(Integer carbsPercentage) {
        this.carbsPercentage = carbsPercentage;
        return this;
    }

    public void setCarbsPercentage(Integer carbsPercentage) {
        this.carbsPercentage = carbsPercentage;
    }

    public Integer getFatPercentage() {
        return fatPercentage;
    }

    public NutritionProgram fatPercentage(Integer fatPercentage) {
        this.fatPercentage = fatPercentage;
        return this;
    }

    public void setFatPercentage(Integer fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public String getDescription() {
        return description;
    }

    public NutritionProgram description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public NutritionProgram meals(Set<Meal> meals) {
        this.meals = meals;
        return this;
    }

    public NutritionProgram addMeals(Meal meal) {
        this.meals.add(meal);
        meal.setNutritionProgram(this);
        return this;
    }

    public NutritionProgram removeMeals(Meal meal) {
        this.meals.remove(meal);
        meal.setNutritionProgram(null);
        return this;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public AdjustProgram getProgram() {
        return program;
    }

    public NutritionProgram program(AdjustProgram adjustProgram) {
        this.program = adjustProgram;
        return this;
    }

    public void setProgram(AdjustProgram adjustProgram) {
        this.program = adjustProgram;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutritionProgram)) {
            return false;
        }
        return id != null && id.equals(((NutritionProgram) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NutritionProgram{" +
            "id=" + getId() +
            ", dailyCalories=" + getDailyCalories() +
            ", proteinPercentage=" + getProteinPercentage() +
            ", carbsPercentage=" + getCarbsPercentage() +
            ", fatPercentage=" + getFatPercentage() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
