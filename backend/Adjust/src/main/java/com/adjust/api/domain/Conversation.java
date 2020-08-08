package com.adjust.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Conversation.
 */
@Entity
@Table(name = "conversation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "specialist_id")
    private Long specialistId;

    @OneToMany(mappedBy = "conversation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ChatMessage> messages = new HashSet<>();

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

    public Conversation clientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public Conversation specialistId(Long specialistId) {
        this.specialistId = specialistId;
        return this;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public Set<ChatMessage> getMessages() {
        return messages;
    }

    public Conversation messages(Set<ChatMessage> chatMessages) {
        this.messages = chatMessages;
        return this;
    }

    public Conversation addMessages(ChatMessage chatMessage) {
        this.messages.add(chatMessage);
        chatMessage.setConversation(this);
        return this;
    }

    public Conversation removeMessages(ChatMessage chatMessage) {
        this.messages.remove(chatMessage);
        chatMessage.setConversation(null);
        return this;
    }

    public void setMessages(Set<ChatMessage> chatMessages) {
        this.messages = chatMessages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Conversation)) {
            return false;
        }
        return id != null && id.equals(((Conversation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Conversation{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", specialistId=" + getSpecialistId() +
            "}";
    }
}
