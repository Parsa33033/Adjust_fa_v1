package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;
import com.adjust.api.domain.enumeration.Gender;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustClient} entity.
 */
public class AdjustClientDTO implements Serializable {
    
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Integer age;

    private Gender gender;

    private Double token;

    private Double score;

    @Lob
    private byte[] image;

    private String imageContentType;
    
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getToken() {
        return token;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustClientDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjustClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustClientDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", age=" + getAge() +
            ", gender='" + getGender() + "'" +
            ", token=" + getToken() +
            ", score=" + getScore() +
            ", image='" + getImage() + "'" +
            "}";
    }
}
