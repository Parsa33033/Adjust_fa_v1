package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;
import com.adjust.api.domain.enumeration.Gender;

/**
 * A DTO for the {@link com.adjust.api.domain.BodyComposition} entity.
 */
public class BodyCompositionDTO implements Serializable {
    
    private Long id;

    private LocalDate createdAt;

    private Double height;

    private Double weight;

    private Double bmi;

    private Double wrist;

    private Double waist;

    private Double lbm;

    private Double muscleMass;

    private Integer muscleMassPercentage;

    private Double fatMass;

    private Integer fatMassPercentage;

    private Gender gender;

    private Integer age;

    @Lob
    private byte[] bodyImage;

    private String bodyImageContentType;
    @Lob
    private byte[] bodyCompositionFile;

    private String bodyCompositionFileContentType;
    @Lob
    private byte[] bloodTestFile;

    private String bloodTestFileContentType;

    private Long programId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Double getWrist() {
        return wrist;
    }

    public void setWrist(Double wrist) {
        this.wrist = wrist;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getLbm() {
        return lbm;
    }

    public void setLbm(Double lbm) {
        this.lbm = lbm;
    }

    public Double getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(Double muscleMass) {
        this.muscleMass = muscleMass;
    }

    public Integer getMuscleMassPercentage() {
        return muscleMassPercentage;
    }

    public void setMuscleMassPercentage(Integer muscleMassPercentage) {
        this.muscleMassPercentage = muscleMassPercentage;
    }

    public Double getFatMass() {
        return fatMass;
    }

    public void setFatMass(Double fatMass) {
        this.fatMass = fatMass;
    }

    public Integer getFatMassPercentage() {
        return fatMassPercentage;
    }

    public void setFatMassPercentage(Integer fatMassPercentage) {
        this.fatMassPercentage = fatMassPercentage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getBodyImage() {
        return bodyImage;
    }

    public void setBodyImage(byte[] bodyImage) {
        this.bodyImage = bodyImage;
    }

    public String getBodyImageContentType() {
        return bodyImageContentType;
    }

    public void setBodyImageContentType(String bodyImageContentType) {
        this.bodyImageContentType = bodyImageContentType;
    }

    public byte[] getBodyCompositionFile() {
        return bodyCompositionFile;
    }

    public void setBodyCompositionFile(byte[] bodyCompositionFile) {
        this.bodyCompositionFile = bodyCompositionFile;
    }

    public String getBodyCompositionFileContentType() {
        return bodyCompositionFileContentType;
    }

    public void setBodyCompositionFileContentType(String bodyCompositionFileContentType) {
        this.bodyCompositionFileContentType = bodyCompositionFileContentType;
    }

    public byte[] getBloodTestFile() {
        return bloodTestFile;
    }

    public void setBloodTestFile(byte[] bloodTestFile) {
        this.bloodTestFile = bloodTestFile;
    }

    public String getBloodTestFileContentType() {
        return bloodTestFileContentType;
    }

    public void setBloodTestFileContentType(String bloodTestFileContentType) {
        this.bloodTestFileContentType = bloodTestFileContentType;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long adjustProgramId) {
        this.programId = adjustProgramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BodyCompositionDTO)) {
            return false;
        }

        return id != null && id.equals(((BodyCompositionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BodyCompositionDTO{" +
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
            ", bodyCompositionFile='" + getBodyCompositionFile() + "'" +
            ", bloodTestFile='" + getBloodTestFile() + "'" +
            ", programId=" + getProgramId() +
            "}";
    }
}
