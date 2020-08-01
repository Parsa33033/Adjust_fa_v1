package com.adjust.api.service;

import com.adjust.api.domain.FitnessProgram;
import com.adjust.api.repository.FitnessProgramRepository;
import com.adjust.api.service.dto.FitnessProgramDTO;
import com.adjust.api.service.mapper.FitnessProgramMapper;
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
 * Service Implementation for managing {@link FitnessProgram}.
 */
@Service
@Transactional
public class FitnessProgramService {

    private final Logger log = LoggerFactory.getLogger(FitnessProgramService.class);

    private final FitnessProgramRepository fitnessProgramRepository;

    private final FitnessProgramMapper fitnessProgramMapper;

    public FitnessProgramService(FitnessProgramRepository fitnessProgramRepository, FitnessProgramMapper fitnessProgramMapper) {
        this.fitnessProgramRepository = fitnessProgramRepository;
        this.fitnessProgramMapper = fitnessProgramMapper;
    }

    /**
     * Save a fitnessProgram.
     *
     * @param fitnessProgramDTO the entity to save.
     * @return the persisted entity.
     */
    public FitnessProgramDTO save(FitnessProgramDTO fitnessProgramDTO) {
        log.debug("Request to save FitnessProgram : {}", fitnessProgramDTO);
        FitnessProgram fitnessProgram = fitnessProgramMapper.toEntity(fitnessProgramDTO);
        fitnessProgram = fitnessProgramRepository.save(fitnessProgram);
        return fitnessProgramMapper.toDto(fitnessProgram);
    }

    /**
     * Get all the fitnessPrograms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FitnessProgramDTO> findAll() {
        log.debug("Request to get all FitnessPrograms");
        return fitnessProgramRepository.findAll().stream()
            .map(fitnessProgramMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the fitnessPrograms where Program is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<FitnessProgramDTO> findAllWhereProgramIsNull() {
        log.debug("Request to get all fitnessPrograms where Program is null");
        return StreamSupport
            .stream(fitnessProgramRepository.findAll().spliterator(), false)
            .filter(fitnessProgram -> fitnessProgram.getProgram() == null)
            .map(fitnessProgramMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fitnessProgram by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FitnessProgramDTO> findOne(Long id) {
        log.debug("Request to get FitnessProgram : {}", id);
        return fitnessProgramRepository.findById(id)
            .map(fitnessProgramMapper::toDto);
    }

    /**
     * Delete the fitnessProgram by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FitnessProgram : {}", id);
        fitnessProgramRepository.deleteById(id);
    }
}
