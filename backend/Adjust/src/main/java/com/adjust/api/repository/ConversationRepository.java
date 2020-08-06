package com.adjust.api.repository;

import com.adjust.api.domain.AdjustClient;
import com.adjust.api.domain.Conversation;

import com.adjust.api.domain.Specialist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Conversation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByClientAndSpecialist(AdjustClient client, Specialist specialist);
}
