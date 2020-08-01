package com.adjust.api.service;

import com.adjust.api.domain.AdjustProgram;
import com.adjust.api.repository.AdjustProgramRepository;
import com.adjust.api.service.dto.AdjustProgramDTO;
import com.adjust.api.service.mapper.AdjustProgramMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustProgram}.
 */
@Service
@Transactional
public class AdjustProgramService {

    private final Logger log = LoggerFactory.getLogger(AdjustProgramService.class);

    private final AdjustProgramRepository adjustProgramRepository;

    private final AdjustProgramMapper adjustProgramMapper;

    public AdjustProgramService(AdjustProgramRepository adjustProgramRepository, AdjustProgramMapper adjustProgramMapper) {
        this.adjustProgramRepository = adjustProgramRepository;
        this.adjustProgramMapper = adjustProgramMapper;
    }

    /**
     * Save a adjustProgram.
     *
     * @param adjustProgramDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustProgramDTO save(AdjustProgramDTO adjustProgramDTO) {
        log.debug("Request to save AdjustProgram : {}", adjustProgramDTO);
        AdjustProgram adjustProgram = adjustProgramMapper.toEntity(adjustProgramDTO);
        adjustProgram = adjustProgramRepository.save(adjustProgram);
        return adjustProgramMapper.toDto(adjustProgram);
    }

    /**
     * Get all the adjustPrograms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustProgramDTO> findAll() {
        log.debug("Request to get all AdjustPrograms");
        return adjustProgramRepository.findAll().stream()
            .map(adjustProgramMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustProgram by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustProgramDTO> findOne(Long id) {
        log.debug("Request to get AdjustProgram : {}", id);
        return adjustProgramRepository.findById(id)
            .map(adjustProgramMapper::toDto);
    }

    /**
     * Delete the adjustProgram by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustProgram : {}", id);
        adjustProgramRepository.deleteById(id);
    }
}
