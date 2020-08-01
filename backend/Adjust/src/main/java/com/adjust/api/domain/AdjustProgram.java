package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

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
    private BodyComposition bodyCompostion;

    @OneToOne
    @JoinColumn(unique = true)
    private FitnessProgram fitnessProgram;

    @OneToOne
    @JoinColumn(unique = true)
    private NutritionProgram nutritionProgram;

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

    public BodyComposition getBodyCompostion() {
        return bodyCompostion;
    }

    public AdjustProgram bodyCompostion(BodyComposition bodyComposition) {
        this.bodyCompostion = bodyComposition;
        return this;
    }

    public void setBodyCompostion(BodyComposition bodyComposition) {
        this.bodyCompostion = bodyComposition;
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
