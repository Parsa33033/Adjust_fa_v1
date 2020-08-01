package com.adjust.api.service;

import com.adjust.api.domain.AdjustTutorialVideo;
import com.adjust.api.repository.AdjustTutorialVideoRepository;
import com.adjust.api.service.dto.AdjustTutorialVideoDTO;
import com.adjust.api.service.mapper.AdjustTutorialVideoMapper;
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
 * Service Implementation for managing {@link AdjustTutorialVideo}.
 */
@Service
@Transactional
public class AdjustTutorialVideoService {

    private final Logger log = LoggerFactory.getLogger(AdjustTutorialVideoService.class);

    private final AdjustTutorialVideoRepository adjustTutorialVideoRepository;

    private final AdjustTutorialVideoMapper adjustTutorialVideoMapper;

    public AdjustTutorialVideoService(AdjustTutorialVideoRepository adjustTutorialVideoRepository, AdjustTutorialVideoMapper adjustTutorialVideoMapper) {
        this.adjustTutorialVideoRepository = adjustTutorialVideoRepository;
        this.adjustTutorialVideoMapper = adjustTutorialVideoMapper;
    }

    /**
     * Save a adjustTutorialVideo.
     *
     * @param adjustTutorialVideoDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustTutorialVideoDTO save(AdjustTutorialVideoDTO adjustTutorialVideoDTO) {
        log.debug("Request to save AdjustTutorialVideo : {}", adjustTutorialVideoDTO);
        AdjustTutorialVideo adjustTutorialVideo = adjustTutorialVideoMapper.toEntity(adjustTutorialVideoDTO);
        adjustTutorialVideo = adjustTutorialVideoRepository.save(adjustTutorialVideo);
        return adjustTutorialVideoMapper.toDto(adjustTutorialVideo);
    }

    /**
     * Get all the adjustTutorialVideos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustTutorialVideoDTO> findAll() {
        log.debug("Request to get all AdjustTutorialVideos");
        return adjustTutorialVideoRepository.findAll().stream()
            .map(adjustTutorialVideoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the adjustTutorialVideos where Tutorial is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AdjustTutorialVideoDTO> findAllWhereTutorialIsNull() {
        log.debug("Request to get all adjustTutorialVideos where Tutorial is null");
        return StreamSupport
            .stream(adjustTutorialVideoRepository.findAll().spliterator(), false)
            .filter(adjustTutorialVideo -> adjustTutorialVideo.getTutorial() == null)
            .map(adjustTutorialVideoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one adjustTutorialVideo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustTutorialVideoDTO> findOne(Long id) {
        log.debug("Request to get AdjustTutorialVideo : {}", id);
        return adjustTutorialVideoRepository.findById(id)
            .map(adjustTutorialVideoMapper::toDto);
    }

    /**
     * Delete the adjustTutorialVideo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustTutorialVideo : {}", id);
        adjustTutorialVideoRepository.deleteById(id);
    }
}
