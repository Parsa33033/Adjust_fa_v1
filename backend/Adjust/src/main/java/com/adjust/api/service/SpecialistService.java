package com.adjust.api.service;

import com.adjust.api.domain.Specialist;
import com.adjust.api.repository.SpecialistRepository;
import com.adjust.api.service.dto.SpecialistDTO;
import com.adjust.api.service.mapper.SpecialistMapper;
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
 * Service Implementation for managing {@link Specialist}.
 */
@Service
@Transactional
public class SpecialistService {

    private final Logger log = LoggerFactory.getLogger(SpecialistService.class);

    private final SpecialistRepository specialistRepository;

    private final SpecialistMapper specialistMapper;

    public SpecialistService(SpecialistRepository specialistRepository, SpecialistMapper specialistMapper) {
        this.specialistRepository = specialistRepository;
        this.specialistMapper = specialistMapper;
    }

    /**
     * Save a specialist.
     *
     * @param specialistDTO the entity to save.
     * @return the persisted entity.
     */
    public SpecialistDTO save(SpecialistDTO specialistDTO) {
        log.debug("Request to save Specialist : {}", specialistDTO);
        Specialist specialist = specialistMapper.toEntity(specialistDTO);
        specialist = specialistRepository.save(specialist);
        return specialistMapper.toDto(specialist);
    }

    /**
     * Get all the specialists.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SpecialistDTO> findAll() {
        log.debug("Request to get all Specialists");
        return specialistRepository.findAll().stream()
            .map(specialistMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the specialists where Conversation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<SpecialistDTO> findAllWhereConversationIsNull() {
        log.debug("Request to get all specialists where Conversation is null");
        return StreamSupport
            .stream(specialistRepository.findAll().spliterator(), false)
            .filter(specialist -> specialist.getConversation() == null)
            .map(specialistMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one specialist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SpecialistDTO> findOne(Long id) {
        log.debug("Request to get Specialist : {}", id);
        return specialistRepository.findById(id)
            .map(specialistMapper::toDto);
    }

    /**
     * Delete the specialist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Specialist : {}", id);
        specialistRepository.deleteById(id);
    }
}
