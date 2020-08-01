package com.adjust.api.service;

import com.adjust.api.domain.AdjustMove;
import com.adjust.api.repository.AdjustMoveRepository;
import com.adjust.api.service.dto.AdjustMoveDTO;
import com.adjust.api.service.mapper.AdjustMoveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustMove}.
 */
@Service
@Transactional
public class AdjustMoveService {

    private final Logger log = LoggerFactory.getLogger(AdjustMoveService.class);

    private final AdjustMoveRepository adjustMoveRepository;

    private final AdjustMoveMapper adjustMoveMapper;

    public AdjustMoveService(AdjustMoveRepository adjustMoveRepository, AdjustMoveMapper adjustMoveMapper) {
        this.adjustMoveRepository = adjustMoveRepository;
        this.adjustMoveMapper = adjustMoveMapper;
    }

    /**
     * Save a adjustMove.
     *
     * @param adjustMoveDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustMoveDTO save(AdjustMoveDTO adjustMoveDTO) {
        log.debug("Request to save AdjustMove : {}", adjustMoveDTO);
        AdjustMove adjustMove = adjustMoveMapper.toEntity(adjustMoveDTO);
        adjustMove = adjustMoveRepository.save(adjustMove);
        return adjustMoveMapper.toDto(adjustMove);
    }

    /**
     * Get all the adjustMoves.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustMoveDTO> findAll() {
        log.debug("Request to get all AdjustMoves");
        return adjustMoveRepository.findAll().stream()
            .map(adjustMoveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustMove by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustMoveDTO> findOne(Long id) {
        log.debug("Request to get AdjustMove : {}", id);
        return adjustMoveRepository.findById(id)
            .map(adjustMoveMapper::toDto);
    }

    /**
     * Delete the adjustMove by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustMove : {}", id);
        adjustMoveRepository.deleteById(id);
    }
}
