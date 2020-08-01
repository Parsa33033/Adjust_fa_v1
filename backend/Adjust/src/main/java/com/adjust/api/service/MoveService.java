package com.adjust.api.service;

import com.adjust.api.domain.Move;
import com.adjust.api.repository.MoveRepository;
import com.adjust.api.service.dto.MoveDTO;
import com.adjust.api.service.mapper.MoveMapper;
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
 * Service Implementation for managing {@link Move}.
 */
@Service
@Transactional
public class MoveService {

    private final Logger log = LoggerFactory.getLogger(MoveService.class);

    private final MoveRepository moveRepository;

    private final MoveMapper moveMapper;

    public MoveService(MoveRepository moveRepository, MoveMapper moveMapper) {
        this.moveRepository = moveRepository;
        this.moveMapper = moveMapper;
    }

    /**
     * Save a move.
     *
     * @param moveDTO the entity to save.
     * @return the persisted entity.
     */
    public MoveDTO save(MoveDTO moveDTO) {
        log.debug("Request to save Move : {}", moveDTO);
        Move move = moveMapper.toEntity(moveDTO);
        move = moveRepository.save(move);
        return moveMapper.toDto(move);
    }

    /**
     * Get all the moves.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MoveDTO> findAll() {
        log.debug("Request to get all Moves");
        return moveRepository.findAll().stream()
            .map(moveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the moves where Exercise is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MoveDTO> findAllWhereExerciseIsNull() {
        log.debug("Request to get all moves where Exercise is null");
        return StreamSupport
            .stream(moveRepository.findAll().spliterator(), false)
            .filter(move -> move.getExercise() == null)
            .map(moveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one move by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MoveDTO> findOne(Long id) {
        log.debug("Request to get Move : {}", id);
        return moveRepository.findById(id)
            .map(moveMapper::toDto);
    }

    /**
     * Delete the move by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Move : {}", id);
        moveRepository.deleteById(id);
    }
}
