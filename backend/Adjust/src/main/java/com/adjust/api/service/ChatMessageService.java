package com.adjust.api.service;

import com.adjust.api.domain.ChatMessage;
import com.adjust.api.repository.ChatMessageRepository;
import com.adjust.api.service.dto.ChatMessageDTO;
import com.adjust.api.service.mapper.ChatMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ChatMessage}.
 */
@Service
@Transactional
public class ChatMessageService {

    private final Logger log = LoggerFactory.getLogger(ChatMessageService.class);

    private final ChatMessageRepository chatMessageRepository;

    private final ChatMessageMapper chatMessageMapper;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatMessageMapper chatMessageMapper) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageMapper = chatMessageMapper;
    }

    /**
     * Save a chatMessage.
     *
     * @param chatMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public ChatMessageDTO save(ChatMessageDTO chatMessageDTO) {
        log.debug("Request to save ChatMessage : {}", chatMessageDTO);
        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
        chatMessage = chatMessageRepository.save(chatMessage);
        return chatMessageMapper.toDto(chatMessage);
    }

    /**
     * Get all the chatMessages.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ChatMessageDTO> findAll() {
        log.debug("Request to get all ChatMessages");
        return chatMessageRepository.findAll().stream()
            .map(chatMessageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one chatMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChatMessageDTO> findOne(Long id) {
        log.debug("Request to get ChatMessage : {}", id);
        return chatMessageRepository.findById(id)
            .map(chatMessageMapper::toDto);
    }

    /**
     * Delete the chatMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ChatMessage : {}", id);
        chatMessageRepository.deleteById(id);
    }
}
