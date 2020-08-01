package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Conversation} entity.
 */
public class ConversationDTO implements Serializable {
    
    private Long id;


    private Long clientId;

    private Long specialistId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long adjustClientId) {
        this.clientId = adjustClientId;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConversationDTO)) {
            return false;
        }

        return id != null && id.equals(((ConversationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConversationDTO{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", specialistId=" + getSpecialistId() +
            "}";
    }
}
