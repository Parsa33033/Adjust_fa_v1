package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.adjust.api.domain.enumeration.Gender;

/**
 * A AdjustClient.
 */
@Entity
@Table(name = "adjust_client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjustClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "token")
    private Double token;

    @Column(name = "score")
    private Double score;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Tutorial> tutorials = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AdjustProgram> programs = new HashSet<>();

    @OneToOne(mappedBy = "client")
    @JsonIgnore
    private Conversation conversation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public AdjustClient username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public AdjustClient firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AdjustClient lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public AdjustClient birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public AdjustClient age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public AdjustClient gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getToken() {
        return token;
    }

    public AdjustClient token(Double token) {
        this.token = token;
        return this;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public Double getScore() {
        return score;
    }

    public AdjustClient score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public byte[] getImage() {
        return image;
    }

    public AdjustClient image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public AdjustClient imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Set<Tutorial> getTutorials() {
        return tutorials;
    }

    public AdjustClient tutorials(Set<Tutorial> tutorials) {
        this.tutorials = tutorials;
        return this;
    }

    public AdjustClient addTutorials(Tutorial tutorial) {
        this.tutorials.add(tutorial);
        tutorial.setClient(this);
        return this;
    }

    public AdjustClient removeTutorials(Tutorial tutorial) {
        this.tutorials.remove(tutorial);
        tutorial.setClient(null);
        return this;
    }

    public void setTutorials(Set<Tutorial> tutorials) {
        this.tutorials = tutorials;
    }

    public Set<AdjustProgram> getPrograms() {
        return programs;
    }

    public AdjustClient programs(Set<AdjustProgram> adjustPrograms) {
        this.programs = adjustPrograms;
        return this;
    }

    public AdjustClient addPrograms(AdjustProgram adjustProgram) {
        this.programs.add(adjustProgram);
        adjustProgram.setClient(this);
        return this;
    }

    public AdjustClient removePrograms(AdjustProgram adjustProgram) {
        this.programs.remove(adjustProgram);
        adjustProgram.setClient(null);
        return this;
    }

    public void setPrograms(Set<AdjustProgram> adjustPrograms) {
        this.programs = adjustPrograms;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public AdjustClient conversation(Conversation conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustClient)) {
            return false;
        }
        return id != null && id.equals(((AdjustClient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustClient{" +
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
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
