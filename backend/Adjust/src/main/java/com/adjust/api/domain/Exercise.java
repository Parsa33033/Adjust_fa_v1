package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Exercise.
 */
@Entity
@Table(name = "exercise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "reps_min")
    private Integer repsMin;

    @Column(name = "reps_max")
    private Integer repsMax;

    @OneToOne
    @JoinColumn(unique = true)
    private Move move;

    @ManyToOne
    @JsonIgnoreProperties(value = "exercises", allowSetters = true)
    private Workout workout;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public Exercise number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSets() {
        return sets;
    }

    public Exercise sets(Integer sets) {
        this.sets = sets;
        return this;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getRepsMin() {
        return repsMin;
    }

    public Exercise repsMin(Integer repsMin) {
        this.repsMin = repsMin;
        return this;
    }

    public void setRepsMin(Integer repsMin) {
        this.repsMin = repsMin;
    }

    public Integer getRepsMax() {
        return repsMax;
    }

    public Exercise repsMax(Integer repsMax) {
        this.repsMax = repsMax;
        return this;
    }

    public void setRepsMax(Integer repsMax) {
        this.repsMax = repsMax;
    }

    public Move getMove() {
        return move;
    }

    public Exercise move(Move move) {
        this.move = move;
        return this;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Workout getWorkout() {
        return workout;
    }

    public Exercise workout(Workout workout) {
        this.workout = workout;
        return this;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exercise)) {
            return false;
        }
        return id != null && id.equals(((Exercise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Exercise{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", sets=" + getSets() +
            ", repsMin=" + getRepsMin() +
            ", repsMax=" + getRepsMax() +
            "}";
    }
}
