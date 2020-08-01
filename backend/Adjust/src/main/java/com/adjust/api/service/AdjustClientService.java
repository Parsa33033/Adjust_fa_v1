package com.adjust.api.service;

import com.adjust.api.domain.AdjustClient;
import com.adjust.api.repository.AdjustClientRepository;
import com.adjust.api.service.dto.AdjustClientDTO;
import com.adjust.api.service.mapper.AdjustClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link AdjustClient}.
 */
@Service
@Transactional
public class AdjustClientService {

    private final Logger log = LoggerFactory.getLogger(AdjustClientService.class);

    private final AdjustClientRepository adjustClientRepository;

    private final AdjustClientMapper adjustClientMapper;

    public AdjustClientService(AdjustClientRepository adjustClientRepository, AdjustClientMapper adjustClientMapper) {
        this.adjustClientRepository = adjustClientRepository;
        this.adjustClientMapper = adjustClientMapper;
    }

    /**
     * Save a adjustClient.
     *
     * @param adjustClientDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustClientDTO save(AdjustClientDTO adjustClientDTO) {
        log.debug("Request to save AdjustClient : {}", adjustClientDTO);
        AdjustClient adjustClient = adjustClientMapper.toEntity(adjustClientDTO);
        adjustClient = adjustClientRepository.save(adjustClient);
        return adjustClientMapper.toDto(adjustClient);
    }

    /**
     * Get all the adjustClients.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustClientDTO> findAll() {
        log.debug("Request to get all AdjustClients");
        return adjustClientRepository.findAll().stream()
            .map(adjustClientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the adjustClients where Conversation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AdjustClientDTO> findAllWhereConversationIsNull() {
        log.debug("Request to get all adjustClients where Conversation is null");
        return StreamSupport
            .stream(adjustClientRepository.findAll().spliterator(), false)
            .filter(adjustClient -> adjustClient.getConversation() == null)
            .map(adjustClientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one adjustClient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustClientDTO> findOne(Long id) {
        log.debug("Request to get AdjustClient : {}", id);
        return adjustClientRepository.findById(id)
            .map(adjustClientMapper::toDto);
    }

    /**
     * Delete the adjustClient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustClient : {}", id);
        adjustClientRepository.deleteById(id);
    }
}
