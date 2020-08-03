package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A AdjustProgram.
 */
@Entity
@Table(name = "adjust_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjustProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "designed")
    private Boolean designed;

    @Column(name = "done")
    private Boolean done;

    @Column(name = "paid")
    private Boolean paid;

    @OneToOne
    @JoinColumn(unique = true)
    private FitnessProgram fitnessProgram;

    @OneToOne
    @JoinColumn(unique = true)
    private NutritionProgram nutritionProgram;

    @OneToMany(mappedBy = "adjustProgram", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProgramDevelopment> programDevelopments = new HashSet<>();

    @OneToMany(mappedBy = "program", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BodyComposition> bodyCompostions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "programs", allowSetters = true)
    private AdjustClient client;

    @ManyToOne
    @JsonIgnoreProperties(value = "programs", allowSetters = true)
    private Specialist specialist;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public AdjustProgram createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public AdjustProgram expirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean isDesigned() {
        return designed;
    }

    public AdjustProgram designed(Boolean designed) {
        this.designed = designed;
        return this;
    }

    public void setDesigned(Boolean designed) {
        this.designed = designed;
    }

    public Boolean isDone() {
        return done;
    }

    public AdjustProgram done(Boolean done) {
        this.done = done;
        return this;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean isPaid() {
        return paid;
    }

    public AdjustProgram paid(Boolean paid) {
        this.paid = paid;
        return this;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public FitnessProgram getFitnessProgram() {
        return fitnessProgram;
    }

    public AdjustProgram fitnessProgram(FitnessProgram fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
        return this;
    }

    public void setFitnessProgram(FitnessProgram fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
    }

    public NutritionProgram getNutritionProgram() {
        return nutritionProgram;
    }

    public AdjustProgram nutritionProgram(NutritionProgram nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
        return this;
    }

    public void setNutritionProgram(NutritionProgram nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
    }

    public Set<ProgramDevelopment> getProgramDevelopments() {
        return programDevelopments;
    }

    public AdjustProgram programDevelopments(Set<ProgramDevelopment> programDevelopments) {
        this.programDevelopments = programDevelopments;
        return this;
    }

    public AdjustProgram addProgramDevelopments(ProgramDevelopment programDevelopment) {
        this.programDevelopments.add(programDevelopment);
        programDevelopment.setAdjustProgram(this);
        return this;
    }

    public AdjustProgram removeProgramDevelopments(ProgramDevelopment programDevelopment) {
        this.programDevelopments.remove(programDevelopment);
        programDevelopment.setAdjustProgram(null);
        return this;
    }

    public void setProgramDevelopments(Set<ProgramDevelopment> programDevelopments) {
        this.programDevelopments = programDevelopments;
    }

    public Set<BodyComposition> getBodyCompostions() {
        return bodyCompostions;
    }

    public AdjustProgram bodyCompostions(Set<BodyComposition> bodyCompositions) {
        this.bodyCompostions = bodyCompositions;
        return this;
    }

    public AdjustProgram addBodyCompostions(BodyComposition bodyComposition) {
        this.bodyCompostions.add(bodyComposition);
        bodyComposition.setProgram(this);
        return this;
    }

    public AdjustProgram removeBodyCompostions(BodyComposition bodyComposition) {
        this.bodyCompostions.remove(bodyComposition);
        bodyComposition.setProgram(null);
        return this;
    }

    public void setBodyCompostions(Set<BodyComposition> bodyCompositions) {
        this.bodyCompostions = bodyCompositions;
    }

    public AdjustClient getClient() {
        return client;
    }

    public AdjustProgram client(AdjustClient adjustClient) {
        this.client = adjustClient;
        return this;
    }

    public void setClient(AdjustClient adjustClient) {
        this.client = adjustClient;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public AdjustProgram specialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustProgram)) {
            return false;
        }
        return id != null && id.equals(((AdjustProgram) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustProgram{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", designed='" + isDesigned() + "'" +
            ", done='" + isDone() + "'" +
            ", paid='" + isPaid() + "'" +
            "}";
    }
}
