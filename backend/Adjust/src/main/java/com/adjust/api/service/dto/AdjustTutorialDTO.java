package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustTutorial} entity.
 */
public class AdjustTutorialDTO implements Serializable {
    
    private Long id;

    private String title;

    private String description;

    @Lob
    private String text;

    @Lob
    private byte[] thumbnail;

    private String thumbnailContentType;
    private Double tokenPrice;


    private Long videoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailContentType() {
        return thumbnailContentType;
    }

    public void setThumbnailContentType(String thumbnailContentType) {
        this.thumbnailContentType = thumbnailContentType;
    }

    public Double getTokenPrice() {
        return tokenPrice;
    }

    public void setTokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long adjustTutorialVideoId) {
        this.videoId = adjustTutorialVideoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustTutorialDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjustTutorialDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustTutorialDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", tokenPrice=" + getTokenPrice() +
            ", videoId=" + getVideoId() +
            "}";
    }
}
