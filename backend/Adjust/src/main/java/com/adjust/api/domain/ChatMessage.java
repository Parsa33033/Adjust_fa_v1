package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ChatMessage.
 */
@Entity
@Table(name = "chat_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_username")
    private String clientUsername;

    @Column(name = "specialist_id")
    private Long specialistId;

    @Column(name = "specialist_username")
    private String specialistUsername;

    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "voice")
    private byte[] voice;

    @Column(name = "voice_content_type")
    private String voiceContentType;

    @ManyToOne
    @JsonIgnoreProperties(value = "messages", allowSetters = true)
    private Conversation conversation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public ChatMessage clientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public ChatMessage clientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
        return this;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public ChatMessage specialistId(Long specialistId) {
        this.specialistId = specialistId;
        return this;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialistUsername() {
        return specialistUsername;
    }

    public ChatMessage specialistUsername(String specialistUsername) {
        this.specialistUsername = specialistUsername;
        return this;
    }

    public void setSpecialistUsername(String specialistUsername) {
        this.specialistUsername = specialistUsername;
    }

    public String getText() {
        return text;
    }

    public ChatMessage text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getVoice() {
        return voice;
    }

    public ChatMessage voice(byte[] voice) {
        this.voice = voice;
        return this;
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public String getVoiceContentType() {
        return voiceContentType;
    }

    public ChatMessage voiceContentType(String voiceContentType) {
        this.voiceContentType = voiceContentType;
        return this;
    }

    public void setVoiceContentType(String voiceContentType) {
        this.voiceContentType = voiceContentType;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public ChatMessage conversation(Conversation conversation) {
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
        if (!(o instanceof ChatMessage)) {
            return false;
        }
        return id != null && id.equals(((ChatMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChatMessage{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", clientUsername='" + getClientUsername() + "'" +
            ", specialistId=" + getSpecialistId() +
            ", specialistUsername='" + getSpecialistUsername() + "'" +
            ", text='" + getText() + "'" +
            ", voice='" + getVoice() + "'" +
            ", voiceContentType='" + getVoiceContentType() + "'" +
            "}";
    }
}
