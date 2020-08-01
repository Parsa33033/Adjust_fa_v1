package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.BodyComposition} entity.
 */
public class BodyCompositionDTO implements Serializable {
    
    private Long id;

    private LocalDate createdAt;

    private Double height;

    private Double weight;

    private Double bmi;

    @Lob
    private byte[] bodyCompositionFile;

    private String bodyCompositionFileContentType;
    @Lob
    private byte[] bloodTestFile;

    private String bloodTestFileContentType;
    
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
            ", bodyCompositionFile='" + getBodyCompositionFile() + "'" +
            ", bloodTestFile='" + getBloodTestFile() + "'" +
            "}";
    }
}
