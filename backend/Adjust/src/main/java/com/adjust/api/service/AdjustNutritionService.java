package com.adjust.api.service;

import com.adjust.api.domain.AdjustNutrition;
import com.adjust.api.repository.AdjustNutritionRepository;
import com.adjust.api.service.dto.AdjustNutritionDTO;
import com.adjust.api.service.mapper.AdjustNutritionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustNutrition}.
 */
@Service
@Transactional
public class AdjustNutritionService {

    private final Logger log = LoggerFactory.getLogger(AdjustNutritionService.class);

    private final AdjustNutritionRepository adjustNutritionRepository;

    private final AdjustNutritionMapper adjustNutritionMapper;

    public AdjustNutritionService(AdjustNutritionRepository adjustNutritionRepository, AdjustNutritionMapper adjustNutritionMapper) {
        this.adjustNutritionRepository = adjustNutritionRepository;
        this.adjustNutritionMapper = adjustNutritionMapper;
    }

    /**
     * Save a adjustNutrition.
     *
     * @param adjustNutritionDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustNutritionDTO save(AdjustNutritionDTO adjustNutritionDTO) {
        log.debug("Request to save AdjustNutrition : {}", adjustNutritionDTO);
        AdjustNutrition adjustNutrition = adjustNutritionMapper.toEntity(adjustNutritionDTO);
        adjustNutrition = adjustNutritionRepository.save(adjustNutrition);
        return adjustNutritionMapper.toDto(adjustNutrition);
    }

    /**
     * Get all the adjustNutritions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustNutritionDTO> findAll() {
        log.debug("Request to get all AdjustNutritions");
        return adjustNutritionRepository.findAll().stream()
            .map(adjustNutritionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustNutrition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustNutritionDTO> findOne(Long id) {
        log.debug("Request to get AdjustNutrition : {}", id);
        return adjustNutritionRepository.findById(id)
            .map(adjustNutritionMapper::toDto);
    }

    /**
     * Delete the adjustNutrition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustNutrition : {}", id);
        adjustNutritionRepository.deleteById(id);
    }
}
