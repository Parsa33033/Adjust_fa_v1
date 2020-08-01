package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A BodyComposition.
 */
@Entity
@Table(name = "body_composition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BodyComposition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "bmi")
    private Double bmi;

    @Lob
    @Column(name = "body_composition_file")
    private byte[] bodyCompositionFile;

    @Column(name = "body_composition_file_content_type")
    private String bodyCompositionFileContentType;

    @Lob
    @Column(name = "blood_test_file")
    private byte[] bloodTestFile;

    @Column(name = "blood_test_file_content_type")
    private String bloodTestFileContentType;

    @OneToOne(mappedBy = "bodyCompostion")
    @JsonIgnore
    private AdjustProgram program;

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

    public BodyComposition createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Double getHeight() {
        return height;
    }

    public BodyComposition height(Double height) {
        this.height = height;
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public BodyComposition weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public BodyComposition bmi(Double bmi) {
        this.bmi = bmi;
        return this;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public byte[] getBodyCompositionFile() {
        return bodyCompositionFile;
    }

    public BodyComposition bodyCompositionFile(byte[] bodyCompositionFile) {
        this.bodyCompositionFile = bodyCompositionFile;
        return this;
    }

    public void setBodyCompositionFile(byte[] bodyCompositionFile) {
        this.bodyCompositionFile = bodyCompositionFile;
    }

    public String getBodyCompositionFileContentType() {
        return bodyCompositionFileContentType;
    }

    public BodyComposition bodyCompositionFileContentType(String bodyCompositionFileContentType) {
        this.bodyCompositionFileContentType = bodyCompositionFileContentType;
        return this;
    }

    public void setBodyCompositionFileContentType(String bodyCompositionFileContentType) {
        this.bodyCompositionFileContentType = bodyCompositionFileContentType;
    }

    public byte[] getBloodTestFile() {
        return bloodTestFile;
    }

    public BodyComposition bloodTestFile(byte[] bloodTestFile) {
        this.bloodTestFile = bloodTestFile;
        return this;
    }

    public void setBloodTestFile(byte[] bloodTestFile) {
        this.bloodTestFile = bloodTestFile;
    }

    public String getBloodTestFileContentType() {
        return bloodTestFileContentType;
    }

    public BodyComposition bloodTestFileContentType(String bloodTestFileContentType) {
        this.bloodTestFileContentType = bloodTestFileContentType;
        return this;
    }

    public void setBloodTestFileContentType(String bloodTestFileContentType) {
        this.bloodTestFileContentType = bloodTestFileContentType;
    }

    public AdjustProgram getProgram() {
        return program;
    }

    public BodyComposition program(AdjustProgram adjustProgram) {
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
        if (!(o instanceof BodyComposition)) {
            return false;
        }
        return id != null && id.equals(((BodyComposition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BodyComposition{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", bmi=" + getBmi() +
            ", bodyCompositionFile='" + getBodyCompositionFile() + "'" +
            ", bodyCompositionFileContentType='" + getBodyCompositionFileContentType() + "'" +
            ", bloodTestFile='" + getBloodTestFile() + "'" +
            ", bloodTestFileContentType='" + getBloodTestFileContentType() + "'" +
            "}";
    }
}
