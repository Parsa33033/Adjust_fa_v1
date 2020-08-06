package com.adjust.api.repository;

import com.adjust.api.domain.ChatMessage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ChatMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByClientIdAndSpecialistId(Long clientId, Long specialistId);
}
