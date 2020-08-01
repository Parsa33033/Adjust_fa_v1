package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FitnessProgram.
 */
@Entity
@Table(name = "fitness_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FitnessProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "program", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Workout> workouts = new HashSet<>();

    @OneToOne(mappedBy = "fitnessProgram")
    @JsonIgnore
    private AdjustProgram program;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public FitnessProgram type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public FitnessProgram description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public FitnessProgram workouts(Set<Workout> workouts) {
        this.workouts = workouts;
        return this;
    }

    public FitnessProgram addWorkouts(Workout workout) {
        this.workouts.add(workout);
        workout.setProgram(this);
        return this;
    }

    public FitnessProgram removeWorkouts(Workout workout) {
        this.workouts.remove(workout);
        workout.setProgram(null);
        return this;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    public AdjustProgram getProgram() {
        return program;
    }

    public FitnessProgram program(AdjustProgram adjustProgram) {
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
        if (!(o instanceof FitnessProgram)) {
            return false;
        }
        return id != null && id.equals(((FitnessProgram) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FitnessProgram{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
