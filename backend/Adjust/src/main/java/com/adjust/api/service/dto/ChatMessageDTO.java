package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.ChatMessage} entity.
 */
public class ChatMessageDTO implements Serializable {
    
    private Long id;

    private Long clientId;

    private String clientUsername;

    private Long specialistId;

    private String specialistUsername;

    private String text;

    @Lob
    private byte[] voice;

    private String voiceContentType;

    private Long conversationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialistUsername() {
        return specialistUsername;
    }

    public void setSpecialistUsername(String specialistUsername) {
        this.specialistUsername = specialistUsername;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getVoice() {
        return voice;
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public String getVoiceContentType() {
        return voiceContentType;
    }

    public void setVoiceContentType(String voiceContentType) {
        this.voiceContentType = voiceContentType;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatMessageDTO)) {
            return false;
        }

        return id != null && id.equals(((ChatMessageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChatMessageDTO{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", clientUsername='" + getClientUsername() + "'" +
            ", specialistId=" + getSpecialistId() +
            ", specialistUsername='" + getSpecialistUsername() + "'" +
            ", text='" + getText() + "'" +
            ", voice='" + getVoice() + "'" +
            ", conversationId=" + getConversationId() +
            "}";
    }
}
