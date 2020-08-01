package com.adjust.api.service;

import com.adjust.api.domain.TutorialVideo;
import com.adjust.api.repository.TutorialVideoRepository;
import com.adjust.api.service.dto.TutorialVideoDTO;
import com.adjust.api.service.mapper.TutorialVideoMapper;
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
 * Service Implementation for managing {@link TutorialVideo}.
 */
@Service
@Transactional
public class TutorialVideoService {

    private final Logger log = LoggerFactory.getLogger(TutorialVideoService.class);

    private final TutorialVideoRepository tutorialVideoRepository;

    private final TutorialVideoMapper tutorialVideoMapper;

    public TutorialVideoService(TutorialVideoRepository tutorialVideoRepository, TutorialVideoMapper tutorialVideoMapper) {
        this.tutorialVideoRepository = tutorialVideoRepository;
        this.tutorialVideoMapper = tutorialVideoMapper;
    }

    /**
     * Save a tutorialVideo.
     *
     * @param tutorialVideoDTO the entity to save.
     * @return the persisted entity.
     */
    public TutorialVideoDTO save(TutorialVideoDTO tutorialVideoDTO) {
        log.debug("Request to save TutorialVideo : {}", tutorialVideoDTO);
        TutorialVideo tutorialVideo = tutorialVideoMapper.toEntity(tutorialVideoDTO);
        tutorialVideo = tutorialVideoRepository.save(tutorialVideo);
        return tutorialVideoMapper.toDto(tutorialVideo);
    }

    /**
     * Get all the tutorialVideos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TutorialVideoDTO> findAll() {
        log.debug("Request to get all TutorialVideos");
        return tutorialVideoRepository.findAll().stream()
            .map(tutorialVideoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the tutorialVideos where Tutorial is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<TutorialVideoDTO> findAllWhereTutorialIsNull() {
        log.debug("Request to get all tutorialVideos where Tutorial is null");
        return StreamSupport
            .stream(tutorialVideoRepository.findAll().spliterator(), false)
            .filter(tutorialVideo -> tutorialVideo.getTutorial() == null)
            .map(tutorialVideoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tutorialVideo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TutorialVideoDTO> findOne(Long id) {
        log.debug("Request to get TutorialVideo : {}", id);
        return tutorialVideoRepository.findById(id)
            .map(tutorialVideoMapper::toDto);
    }

    /**
     * Delete the tutorialVideo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TutorialVideo : {}", id);
        tutorialVideoRepository.deleteById(id);
    }
}
