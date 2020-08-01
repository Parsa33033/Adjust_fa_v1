package com.adjust.api.service;

import com.adjust.api.domain.AdjustTutorial;
import com.adjust.api.repository.AdjustTutorialRepository;
import com.adjust.api.service.dto.AdjustTutorialDTO;
import com.adjust.api.service.mapper.AdjustTutorialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustTutorial}.
 */
@Service
@Transactional
public class AdjustTutorialService {

    private final Logger log = LoggerFactory.getLogger(AdjustTutorialService.class);

    private final AdjustTutorialRepository adjustTutorialRepository;

    private final AdjustTutorialMapper adjustTutorialMapper;

    public AdjustTutorialService(AdjustTutorialRepository adjustTutorialRepository, AdjustTutorialMapper adjustTutorialMapper) {
        this.adjustTutorialRepository = adjustTutorialRepository;
        this.adjustTutorialMapper = adjustTutorialMapper;
    }

    /**
     * Save a adjustTutorial.
     *
     * @param adjustTutorialDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustTutorialDTO save(AdjustTutorialDTO adjustTutorialDTO) {
        log.debug("Request to save AdjustTutorial : {}", adjustTutorialDTO);
        AdjustTutorial adjustTutorial = adjustTutorialMapper.toEntity(adjustTutorialDTO);
        adjustTutorial = adjustTutorialRepository.save(adjustTutorial);
        return adjustTutorialMapper.toDto(adjustTutorial);
    }

    /**
     * Get all the adjustTutorials.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustTutorialDTO> findAll() {
        log.debug("Request to get all AdjustTutorials");
        return adjustTutorialRepository.findAll().stream()
            .map(adjustTutorialMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustTutorial by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustTutorialDTO> findOne(Long id) {
        log.debug("Request to get AdjustTutorial : {}", id);
        return adjustTutorialRepository.findById(id)
            .map(adjustTutorialMapper::toDto);
    }

    /**
     * Delete the adjustTutorial by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustTutorial : {}", id);
        adjustTutorialRepository.deleteById(id);
    }
}
