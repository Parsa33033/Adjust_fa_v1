package com.adjust.api.repository;

import com.adjust.api.domain.Conversation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Conversation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByClientIdAndSpecialistId(Long clientId, Long specialistId);
}
