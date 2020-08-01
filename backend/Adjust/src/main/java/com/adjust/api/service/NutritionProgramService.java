package com.adjust.api.service;

import com.adjust.api.domain.NutritionProgram;
import com.adjust.api.repository.NutritionProgramRepository;
import com.adjust.api.service.dto.NutritionProgramDTO;
import com.adjust.api.service.mapper.NutritionProgramMapper;
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
 * Service Implementation for managing {@link NutritionProgram}.
 */
@Service
@Transactional
public class NutritionProgramService {

    private final Logger log = LoggerFactory.getLogger(NutritionProgramService.class);

    private final NutritionProgramRepository nutritionProgramRepository;

    private final NutritionProgramMapper nutritionProgramMapper;

    public NutritionProgramService(NutritionProgramRepository nutritionProgramRepository, NutritionProgramMapper nutritionProgramMapper) {
        this.nutritionProgramRepository = nutritionProgramRepository;
        this.nutritionProgramMapper = nutritionProgramMapper;
    }

    /**
     * Save a nutritionProgram.
     *
     * @param nutritionProgramDTO the entity to save.
     * @return the persisted entity.
     */
    public NutritionProgramDTO save(NutritionProgramDTO nutritionProgramDTO) {
        log.debug("Request to save NutritionProgram : {}", nutritionProgramDTO);
        NutritionProgram nutritionProgram = nutritionProgramMapper.toEntity(nutritionProgramDTO);
        nutritionProgram = nutritionProgramRepository.save(nutritionProgram);
        return nutritionProgramMapper.toDto(nutritionProgram);
    }

    /**
     * Get all the nutritionPrograms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NutritionProgramDTO> findAll() {
        log.debug("Request to get all NutritionPrograms");
        return nutritionProgramRepository.findAll().stream()
            .map(nutritionProgramMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the nutritionPrograms where Program is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<NutritionProgramDTO> findAllWhereProgramIsNull() {
        log.debug("Request to get all nutritionPrograms where Program is null");
        return StreamSupport
            .stream(nutritionProgramRepository.findAll().spliterator(), false)
            .filter(nutritionProgram -> nutritionProgram.getProgram() == null)
            .map(nutritionProgramMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one nutritionProgram by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NutritionProgramDTO> findOne(Long id) {
        log.debug("Request to get NutritionProgram : {}", id);
        return nutritionProgramRepository.findById(id)
            .map(nutritionProgramMapper::toDto);
    }

    /**
     * Delete the nutritionProgram by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NutritionProgram : {}", id);
        nutritionProgramRepository.deleteById(id);
    }
}
