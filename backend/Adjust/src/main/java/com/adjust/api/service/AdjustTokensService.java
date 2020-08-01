package com.adjust.api.service;

import com.adjust.api.domain.AdjustTokens;
import com.adjust.api.repository.AdjustTokensRepository;
import com.adjust.api.service.dto.AdjustTokensDTO;
import com.adjust.api.service.mapper.AdjustTokensMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustTokens}.
 */
@Service
@Transactional
public class AdjustTokensService {

    private final Logger log = LoggerFactory.getLogger(AdjustTokensService.class);

    private final AdjustTokensRepository adjustTokensRepository;

    private final AdjustTokensMapper adjustTokensMapper;

    public AdjustTokensService(AdjustTokensRepository adjustTokensRepository, AdjustTokensMapper adjustTokensMapper) {
        this.adjustTokensRepository = adjustTokensRepository;
        this.adjustTokensMapper = adjustTokensMapper;
    }

    /**
     * Save a adjustTokens.
     *
     * @param adjustTokensDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustTokensDTO save(AdjustTokensDTO adjustTokensDTO) {
        log.debug("Request to save AdjustTokens : {}", adjustTokensDTO);
        AdjustTokens adjustTokens = adjustTokensMapper.toEntity(adjustTokensDTO);
        adjustTokens = adjustTokensRepository.save(adjustTokens);
        return adjustTokensMapper.toDto(adjustTokens);
    }

    /**
     * Get all the adjustTokens.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustTokensDTO> findAll() {
        log.debug("Request to get all AdjustTokens");
        return adjustTokensRepository.findAll().stream()
            .map(adjustTokensMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustTokens by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustTokensDTO> findOne(Long id) {
        log.debug("Request to get AdjustTokens : {}", id);
        return adjustTokensRepository.findById(id)
            .map(adjustTokensMapper::toDto);
    }

    /**
     * Delete the adjustTokens by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustTokens : {}", id);
        adjustTokensRepository.deleteById(id);
    }
}
