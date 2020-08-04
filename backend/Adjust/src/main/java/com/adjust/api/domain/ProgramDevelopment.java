package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A ProgramDevelopment.
 */
@Entity
@Table(name = "program_development")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProgramDevelopment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "nutrition_score")
    private Double nutritionScore;

    @Column(name = "fitness_score")
    private Double fitnessScore;

    @ManyToOne
    @JsonIgnoreProperties(value = "programDevelopments", allowSetters = true)
    private AdjustProgram adjustProgram;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public ProgramDevelopment date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getNutritionScore() {
        return nutritionScore;
    }

    public ProgramDevelopment nutritionScore(Double nutritionScore) {
        this.nutritionScore = nutritionScore;
        return this;
    }

    public void setNutritionScore(Double nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public Double getFitnessScore() {
        return fitnessScore;
    }

    public ProgramDevelopment fitnessScore(Double fitnessScore) {
        this.fitnessScore = fitnessScore;
        return this;
    }

    public void setFitnessScore(Double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public AdjustProgram getAdjustProgram() {
        return adjustProgram;
    }

    public ProgramDevelopment adjustProgram(AdjustProgram adjustProgram) {
        this.adjustProgram = adjustProgram;
        return this;
    }

    public void setAdjustProgram(AdjustProgram adjustProgram) {
        this.adjustProgram = adjustProgram;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgramDevelopment)) {
            return false;
        }
        return id != null && id.equals(((ProgramDevelopment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProgramDevelopment{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", nutritionScore=" + getNutritionScore() +
            ", fitnessScore=" + getFitnessScore() +
            "}";
    }
}
