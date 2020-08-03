package com.adjust.api.service;

import com.adjust.api.domain.ProgramDevelopment;
import com.adjust.api.repository.ProgramDevelopmentRepository;
import com.adjust.api.service.dto.ProgramDevelopmentDTO;
import com.adjust.api.service.mapper.ProgramDevelopmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProgramDevelopment}.
 */
@Service
@Transactional
public class ProgramDevelopmentService {

    private final Logger log = LoggerFactory.getLogger(ProgramDevelopmentService.class);

    private final ProgramDevelopmentRepository programDevelopmentRepository;

    private final ProgramDevelopmentMapper programDevelopmentMapper;

    public ProgramDevelopmentService(ProgramDevelopmentRepository programDevelopmentRepository, ProgramDevelopmentMapper programDevelopmentMapper) {
        this.programDevelopmentRepository = programDevelopmentRepository;
        this.programDevelopmentMapper = programDevelopmentMapper;
    }

    /**
     * Save a programDevelopment.
     *
     * @param programDevelopmentDTO the entity to save.
     * @return the persisted entity.
     */
    public ProgramDevelopmentDTO save(ProgramDevelopmentDTO programDevelopmentDTO) {
        log.debug("Request to save ProgramDevelopment : {}", programDevelopmentDTO);
        ProgramDevelopment programDevelopment = programDevelopmentMapper.toEntity(programDevelopmentDTO);
        programDevelopment = programDevelopmentRepository.save(programDevelopment);
        return programDevelopmentMapper.toDto(programDevelopment);
    }

    /**
     * Get all the programDevelopments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProgramDevelopmentDTO> findAll() {
        log.debug("Request to get all ProgramDevelopments");
        return programDevelopmentRepository.findAll().stream()
            .map(programDevelopmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one programDevelopment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProgramDevelopmentDTO> findOne(Long id) {
        log.debug("Request to get ProgramDevelopment : {}", id);
        return programDevelopmentRepository.findById(id)
            .map(programDevelopmentMapper::toDto);
    }

    /**
     * Delete the programDevelopment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProgramDevelopment : {}", id);
        programDevelopmentRepository.deleteById(id);
    }
}
