package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TutorialVideo.
 */
@Entity
@Table(name = "tutorial_video")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TutorialVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adjust_tutorial_video_id")
    private Long adjustTutorialVideoId;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "content_content_type")
    private String contentContentType;

    @OneToOne(mappedBy = "video")
    @JsonIgnore
    private Tutorial tutorial;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdjustTutorialVideoId() {
        return adjustTutorialVideoId;
    }

    public TutorialVideo adjustTutorialVideoId(Long adjustTutorialVideoId) {
        this.adjustTutorialVideoId = adjustTutorialVideoId;
        return this;
    }

    public void setAdjustTutorialVideoId(Long adjustTutorialVideoId) {
        this.adjustTutorialVideoId = adjustTutorialVideoId;
    }

    public byte[] getContent() {
        return content;
    }

    public TutorialVideo content(byte[] content) {
        this.content = content;
        return this;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentContentType() {
        return contentContentType;
    }

    public TutorialVideo contentContentType(String contentContentType) {
        this.contentContentType = contentContentType;
        return this;
    }

    public void setContentContentType(String contentContentType) {
        this.contentContentType = contentContentType;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

    public TutorialVideo tutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
        return this;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TutorialVideo)) {
            return false;
        }
        return id != null && id.equals(((TutorialVideo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TutorialVideo{" +
            "id=" + getId() +
            ", adjustTutorialVideoId=" + getAdjustTutorialVideoId() +
            ", content='" + getContent() + "'" +
            ", contentContentType='" + getContentContentType() + "'" +
            "}";
    }
}
