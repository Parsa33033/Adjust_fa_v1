package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;
import com.adjust.api.domain.enumeration.Gender;

/**
 * A DTO for the {@link com.adjust.api.domain.Specialist} entity.
 */
public class SpecialistDTO implements Serializable {
    
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birth;

    private Gender gender;

    private String degree;

    private String field;

    private String resume;

    private Double stars;

    @Lob
    private byte[] image;

    private String imageContentType;
    private Boolean busy;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecialistDTO)) {
            return false;
        }

        return id != null && id.equals(((SpecialistDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpecialistDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", birth='" + getBirth() + "'" +
            ", gender='" + getGender() + "'" +
            ", degree='" + getDegree() + "'" +
            ", field='" + getField() + "'" +
            ", resume='" + getResume() + "'" +
            ", stars=" + getStars() +
            ", image='" + getImage() + "'" +
            ", busy='" + isBusy() + "'" +
            "}";
    }
}
