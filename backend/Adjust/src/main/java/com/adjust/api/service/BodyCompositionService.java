package com.adjust.api.service;

import com.adjust.api.domain.BodyComposition;
import com.adjust.api.repository.BodyCompositionRepository;
import com.adjust.api.service.dto.BodyCompositionDTO;
import com.adjust.api.service.mapper.BodyCompositionMapper;
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
 * Service Implementation for managing {@link BodyComposition}.
 */
@Service
@Transactional
public class BodyCompositionService {

    private final Logger log = LoggerFactory.getLogger(BodyCompositionService.class);

    private final BodyCompositionRepository bodyCompositionRepository;

    private final BodyCompositionMapper bodyCompositionMapper;

    public BodyCompositionService(BodyCompositionRepository bodyCompositionRepository, BodyCompositionMapper bodyCompositionMapper) {
        this.bodyCompositionRepository = bodyCompositionRepository;
        this.bodyCompositionMapper = bodyCompositionMapper;
    }

    /**
     * Save a bodyComposition.
     *
     * @param bodyCompositionDTO the entity to save.
     * @return the persisted entity.
     */
    public BodyCompositionDTO save(BodyCompositionDTO bodyCompositionDTO) {
        log.debug("Request to save BodyComposition : {}", bodyCompositionDTO);
        BodyComposition bodyComposition = bodyCompositionMapper.toEntity(bodyCompositionDTO);
        bodyComposition = bodyCompositionRepository.save(bodyComposition);
        return bodyCompositionMapper.toDto(bodyComposition);
    }

    /**
     * Get all the bodyCompositions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BodyCompositionDTO> findAll() {
        log.debug("Request to get all BodyCompositions");
        return bodyCompositionRepository.findAll().stream()
            .map(bodyCompositionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the bodyCompositions where Program is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BodyCompositionDTO> findAllWhereProgramIsNull() {
        log.debug("Request to get all bodyCompositions where Program is null");
        return StreamSupport
            .stream(bodyCompositionRepository.findAll().spliterator(), false)
            .filter(bodyComposition -> bodyComposition.getProgram() == null)
            .map(bodyCompositionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bodyComposition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BodyCompositionDTO> findOne(Long id) {
        log.debug("Request to get BodyComposition : {}", id);
        return bodyCompositionRepository.findById(id)
            .map(bodyCompositionMapper::toDto);
    }

    /**
     * Delete the bodyComposition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BodyComposition : {}", id);
        bodyCompositionRepository.deleteById(id);
    }
}
