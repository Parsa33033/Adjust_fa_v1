package com.adjust.api.service;

import com.adjust.api.domain.AdjustFood;
import com.adjust.api.repository.AdjustFoodRepository;
import com.adjust.api.service.dto.AdjustFoodDTO;
import com.adjust.api.service.mapper.AdjustFoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustFood}.
 */
@Service
@Transactional
public class AdjustFoodService {

    private final Logger log = LoggerFactory.getLogger(AdjustFoodService.class);

    private final AdjustFoodRepository adjustFoodRepository;

    private final AdjustFoodMapper adjustFoodMapper;

    public AdjustFoodService(AdjustFoodRepository adjustFoodRepository, AdjustFoodMapper adjustFoodMapper) {
        this.adjustFoodRepository = adjustFoodRepository;
        this.adjustFoodMapper = adjustFoodMapper;
    }

    /**
     * Save a adjustFood.
     *
     * @param adjustFoodDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustFoodDTO save(AdjustFoodDTO adjustFoodDTO) {
        log.debug("Request to save AdjustFood : {}", adjustFoodDTO);
        AdjustFood adjustFood = adjustFoodMapper.toEntity(adjustFoodDTO);
        adjustFood = adjustFoodRepository.save(adjustFood);
        return adjustFoodMapper.toDto(adjustFood);
    }

    /**
     * Get all the adjustFoods.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustFoodDTO> findAll() {
        log.debug("Request to get all AdjustFoods");
        return adjustFoodRepository.findAll().stream()
            .map(adjustFoodMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustFood by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustFoodDTO> findOne(Long id) {
        log.debug("Request to get AdjustFood : {}", id);
        return adjustFoodRepository.findById(id)
            .map(adjustFoodMapper::toDto);
    }

    /**
     * Delete the adjustFood by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustFood : {}", id);
        adjustFoodRepository.deleteById(id);
    }
}
