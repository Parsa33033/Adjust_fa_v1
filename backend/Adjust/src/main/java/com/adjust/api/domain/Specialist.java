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
 * A Specialist.
 */
@Entity
@Table(name = "specialist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Specialist implements Serializable {

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

    @Column(name = "birth")
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "degree")
    private String degree;

    @Column(name = "field")
    private String field;

    @Column(name = "resume")
    private String resume;

    @Column(name = "stars")
    private Double stars;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "busy")
    private Boolean busy;

    @OneToMany(mappedBy = "specialist")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AdjustProgram> programs = new HashSet<>();

    @OneToOne(mappedBy = "specialist")
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

    public Specialist username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public Specialist firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Specialist lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Specialist birth(LocalDate birth) {
        this.birth = birth;
        return this;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public Specialist gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDegree() {
        return degree;
    }

    public Specialist degree(String degree) {
        this.degree = degree;
        return this;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public Specialist field(String field) {
        this.field = field;
        return this;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getResume() {
        return resume;
    }

    public Specialist resume(String resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Double getStars() {
        return stars;
    }

    public Specialist stars(Double stars) {
        this.stars = stars;
        return this;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public byte[] getImage() {
        return image;
    }

    public Specialist image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Specialist imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isBusy() {
        return busy;
    }

    public Specialist busy(Boolean busy) {
        this.busy = busy;
        return this;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    public Set<AdjustProgram> getPrograms() {
        return programs;
    }

    public Specialist programs(Set<AdjustProgram> adjustPrograms) {
        this.programs = adjustPrograms;
        return this;
    }

    public Specialist addPrograms(AdjustProgram adjustProgram) {
        this.programs.add(adjustProgram);
        adjustProgram.setSpecialist(this);
        return this;
    }

    public Specialist removePrograms(AdjustProgram adjustProgram) {
        this.programs.remove(adjustProgram);
        adjustProgram.setSpecialist(null);
        return this;
    }

    public void setPrograms(Set<AdjustProgram> adjustPrograms) {
        this.programs = adjustPrograms;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Specialist conversation(Conversation conversation) {
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
        if (!(o instanceof Specialist)) {
            return false;
        }
        return id != null && id.equals(((Specialist) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Specialist{" +
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
            ", imageContentType='" + getImageContentType() + "'" +
            ", busy='" + isBusy() + "'" +
            "}";
    }
}
