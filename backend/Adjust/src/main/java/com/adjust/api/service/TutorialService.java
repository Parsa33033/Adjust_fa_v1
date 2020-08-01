package com.adjust.api.service;

import com.adjust.api.domain.Tutorial;
import com.adjust.api.repository.TutorialRepository;
import com.adjust.api.service.dto.TutorialDTO;
import com.adjust.api.service.mapper.TutorialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Tutorial}.
 */
@Service
@Transactional
public class TutorialService {

    private final Logger log = LoggerFactory.getLogger(TutorialService.class);

    private final TutorialRepository tutorialRepository;

    private final TutorialMapper tutorialMapper;

    public TutorialService(TutorialRepository tutorialRepository, TutorialMapper tutorialMapper) {
        this.tutorialRepository = tutorialRepository;
        this.tutorialMapper = tutorialMapper;
    }

    /**
     * Save a tutorial.
     *
     * @param tutorialDTO the entity to save.
     * @return the persisted entity.
     */
    public TutorialDTO save(TutorialDTO tutorialDTO) {
        log.debug("Request to save Tutorial : {}", tutorialDTO);
        Tutorial tutorial = tutorialMapper.toEntity(tutorialDTO);
        tutorial = tutorialRepository.save(tutorial);
        return tutorialMapper.toDto(tutorial);
    }

    /**
     * Get all the tutorials.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TutorialDTO> findAll() {
        log.debug("Request to get all Tutorials");
        return tutorialRepository.findAll().stream()
            .map(tutorialMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tutorial by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TutorialDTO> findOne(Long id) {
        log.debug("Request to get Tutorial : {}", id);
        return tutorialRepository.findById(id)
            .map(tutorialMapper::toDto);
    }

    /**
     * Delete the tutorial by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tutorial : {}", id);
        tutorialRepository.deleteById(id);
    }
}
