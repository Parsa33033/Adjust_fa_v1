package com.adjust.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AdjustTutorial.
 */
@Entity
@Table(name = "adjust_tutorial")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjustTutorial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @Column(name = "thumbnail_content_type")
    private String thumbnailContentType;

    @Column(name = "token_price")
    private Double tokenPrice;

    @OneToOne
    @JoinColumn(unique = true)
    private AdjustTutorialVideo video;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public AdjustTutorial title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public AdjustTutorial description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public AdjustTutorial text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public AdjustTutorial thumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailContentType() {
        return thumbnailContentType;
    }

    public AdjustTutorial thumbnailContentType(String thumbnailContentType) {
        this.thumbnailContentType = thumbnailContentType;
        return this;
    }

    public void setThumbnailContentType(String thumbnailContentType) {
        this.thumbnailContentType = thumbnailContentType;
    }

    public Double getTokenPrice() {
        return tokenPrice;
    }

    public AdjustTutorial tokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
        return this;
    }

    public void setTokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public AdjustTutorialVideo getVideo() {
        return video;
    }

    public AdjustTutorial video(AdjustTutorialVideo adjustTutorialVideo) {
        this.video = adjustTutorialVideo;
        return this;
    }

    public void setVideo(AdjustTutorialVideo adjustTutorialVideo) {
        this.video = adjustTutorialVideo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustTutorial)) {
            return false;
        }
        return id != null && id.equals(((AdjustTutorial) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustTutorial{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", thumbnailContentType='" + getThumbnailContentType() + "'" +
            ", tokenPrice=" + getTokenPrice() +
            "}";
    }
}
