package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AdjustTutorialVideo.
 */
@Entity
@Table(name = "adjust_tutorial_video")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjustTutorialVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "content_content_type")
    private String contentContentType;

    @OneToOne(mappedBy = "video")
    @JsonIgnore
    private AdjustTutorial tutorial;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public AdjustTutorialVideo content(byte[] content) {
        this.content = content;
        return this;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentContentType() {
        return contentContentType;
    }

    public AdjustTutorialVideo contentContentType(String contentContentType) {
        this.contentContentType = contentContentType;
        return this;
    }

    public void setContentContentType(String contentContentType) {
        this.contentContentType = contentContentType;
    }

    public AdjustTutorial getTutorial() {
        return tutorial;
    }

    public AdjustTutorialVideo tutorial(AdjustTutorial adjustTutorial) {
        this.tutorial = adjustTutorial;
        return this;
    }

    public void setTutorial(AdjustTutorial adjustTutorial) {
        this.tutorial = adjustTutorial;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustTutorialVideo)) {
            return false;
        }
        return id != null && id.equals(((AdjustTutorialVideo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustTutorialVideo{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", contentContentType='" + getContentContentType() + "'" +
            "}";
    }
}
