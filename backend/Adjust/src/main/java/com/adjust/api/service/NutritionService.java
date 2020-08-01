package com.adjust.api.service;

import com.adjust.api.domain.Nutrition;
import com.adjust.api.repository.NutritionRepository;
import com.adjust.api.service.dto.NutritionDTO;
import com.adjust.api.service.mapper.NutritionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Nutrition}.
 */
@Service
@Transactional
public class NutritionService {

    private final Logger log = LoggerFactory.getLogger(NutritionService.class);

    private final NutritionRepository nutritionRepository;

    private final NutritionMapper nutritionMapper;

    public NutritionService(NutritionRepository nutritionRepository, NutritionMapper nutritionMapper) {
        this.nutritionRepository = nutritionRepository;
        this.nutritionMapper = nutritionMapper;
    }

    /**
     * Save a nutrition.
     *
     * @param nutritionDTO the entity to save.
     * @return the persisted entity.
     */
    public NutritionDTO save(NutritionDTO nutritionDTO) {
        log.debug("Request to save Nutrition : {}", nutritionDTO);
        Nutrition nutrition = nutritionMapper.toEntity(nutritionDTO);
        nutrition = nutritionRepository.save(nutrition);
        return nutritionMapper.toDto(nutrition);
    }

    /**
     * Get all the nutritions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NutritionDTO> findAll() {
        log.debug("Request to get all Nutritions");
        return nutritionRepository.findAll().stream()
            .map(nutritionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nutrition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NutritionDTO> findOne(Long id) {
        log.debug("Request to get Nutrition : {}", id);
        return nutritionRepository.findById(id)
            .map(nutritionMapper::toDto);
    }

    /**
     * Delete the nutrition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Nutrition : {}", id);
        nutritionRepository.deleteById(id);
    }
}
