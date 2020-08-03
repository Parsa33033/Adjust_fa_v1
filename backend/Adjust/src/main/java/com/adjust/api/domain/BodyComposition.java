package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.adjust.api.domain.enumeration.Gender;

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

    @Column(name = "wrist")
    private Double wrist;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "lbm")
    private Double lbm;

    @Column(name = "muscle_mass")
    private Double muscleMass;

    @Column(name = "muscle_mass_percentage")
    private Integer muscleMassPercentage;

    @Column(name = "fat_mass")
    private Double fatMass;

    @Column(name = "fat_mass_percentage")
    private Integer fatMassPercentage;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "age")
    private Integer age;

    @Lob
    @Column(name = "body_image")
    private byte[] bodyImage;

    @Column(name = "body_image_content_type")
    private String bodyImageContentType;

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

    @ManyToOne
    @JsonIgnoreProperties(value = "bodyCompostions", allowSetters = true)
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

    public Double getWrist() {
        return wrist;
    }

    public BodyComposition wrist(Double wrist) {
        this.wrist = wrist;
        return this;
    }

    public void setWrist(Double wrist) {
        this.wrist = wrist;
    }

    public Double getWaist() {
        return waist;
    }

    public BodyComposition waist(Double waist) {
        this.waist = waist;
        return this;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getLbm() {
        return lbm;
    }

    public BodyComposition lbm(Double lbm) {
        this.lbm = lbm;
        return this;
    }

    public void setLbm(Double lbm) {
        this.lbm = lbm;
    }

    public Double getMuscleMass() {
        return muscleMass;
    }

    public BodyComposition muscleMass(Double muscleMass) {
        this.muscleMass = muscleMass;
        return this;
    }

    public void setMuscleMass(Double muscleMass) {
        this.muscleMass = muscleMass;
    }

    public Integer getMuscleMassPercentage() {
        return muscleMassPercentage;
    }

    public BodyComposition muscleMassPercentage(Integer muscleMassPercentage) {
        this.muscleMassPercentage = muscleMassPercentage;
        return this;
    }

    public void setMuscleMassPercentage(Integer muscleMassPercentage) {
        this.muscleMassPercentage = muscleMassPercentage;
    }

    public Double getFatMass() {
        return fatMass;
    }

    public BodyComposition fatMass(Double fatMass) {
        this.fatMass = fatMass;
        return this;
    }

    public void setFatMass(Double fatMass) {
        this.fatMass = fatMass;
    }

    public Integer getFatMassPercentage() {
        return fatMassPercentage;
    }

    public BodyComposition fatMassPercentage(Integer fatMassPercentage) {
        this.fatMassPercentage = fatMassPercentage;
        return this;
    }

    public void setFatMassPercentage(Integer fatMassPercentage) {
        this.fatMassPercentage = fatMassPercentage;
    }

    public Gender getGender() {
        return gender;
    }

    public BodyComposition gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public BodyComposition age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getBodyImage() {
        return bodyImage;
    }

    public BodyComposition bodyImage(byte[] bodyImage) {
        this.bodyImage = bodyImage;
        return this;
    }

    public void setBodyImage(byte[] bodyImage) {
        this.bodyImage = bodyImage;
    }

    public String getBodyImageContentType() {
        return bodyImageContentType;
    }

    public BodyComposition bodyImageContentType(String bodyImageContentType) {
        this.bodyImageContentType = bodyImageContentType;
        return this;
    }

    public void setBodyImageContentType(String bodyImageContentType) {
        this.bodyImageContentType = bodyImageContentType;
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
            ", wrist=" + getWrist() +
            ", waist=" + getWaist() +
            ", lbm=" + getLbm() +
            ", muscleMass=" + getMuscleMass() +
            ", muscleMassPercentage=" + getMuscleMassPercentage() +
            ", fatMass=" + getFatMass() +
            ", fatMassPercentage=" + getFatMassPercentage() +
            ", gender='" + getGender() + "'" +
            ", age=" + getAge() +
            ", bodyImage='" + getBodyImage() + "'" +
            ", bodyImageContentType='" + getBodyImageContentType() + "'" +
            ", bodyCompositionFile='" + getBodyCompositionFile() + "'" +
            ", bodyCompositionFileContentType='" + getBodyCompositionFileContentType() + "'" +
            ", bloodTestFile='" + getBloodTestFile() + "'" +
            ", bloodTestFileContentType='" + getBloodTestFileContentType() + "'" +
            "}";
    }
}
