package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.Tutorial} entity.
 */
public class TutorialDTO implements Serializable {
    
    private Long id;

    private String title;

    private String description;

    @Lob
    private String text;

    @Lob
    private byte[] thumbnail;

    private String thumbnailContentType;
    private Double tokenPrice;

    private Long adjustTutorialId;


    private Long videoId;

    private Long clientId;
    
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

    public Long getAdjustTutorialId() {
        return adjustTutorialId;
    }

    public void setAdjustTutorialId(Long adjustTutorialId) {
        this.adjustTutorialId = adjustTutorialId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long tutorialVideoId) {
        this.videoId = tutorialVideoId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long adjustClientId) {
        this.clientId = adjustClientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TutorialDTO)) {
            return false;
        }

        return id != null && id.equals(((TutorialDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TutorialDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", tokenPrice=" + getTokenPrice() +
            ", adjustTutorialId=" + getAdjustTutorialId() +
            ", videoId=" + getVideoId() +
            ", clientId=" + getClientId() +
            "}";
    }
}
