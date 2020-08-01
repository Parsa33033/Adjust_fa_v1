package com.adjust.api.service;

import com.adjust.api.domain.Workout;
import com.adjust.api.repository.WorkoutRepository;
import com.adjust.api.service.dto.WorkoutDTO;
import com.adjust.api.service.mapper.WorkoutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Workout}.
 */
@Service
@Transactional
public class WorkoutService {

    private final Logger log = LoggerFactory.getLogger(WorkoutService.class);

    private final WorkoutRepository workoutRepository;

    private final WorkoutMapper workoutMapper;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    /**
     * Save a workout.
     *
     * @param workoutDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkoutDTO save(WorkoutDTO workoutDTO) {
        log.debug("Request to save Workout : {}", workoutDTO);
        Workout workout = workoutMapper.toEntity(workoutDTO);
        workout = workoutRepository.save(workout);
        return workoutMapper.toDto(workout);
    }

    /**
     * Get all the workouts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WorkoutDTO> findAll() {
        log.debug("Request to get all Workouts");
        return workoutRepository.findAll().stream()
            .map(workoutMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one workout by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkoutDTO> findOne(Long id) {
        log.debug("Request to get Workout : {}", id);
        return workoutRepository.findById(id)
            .map(workoutMapper::toDto);
    }

    /**
     * Delete the workout by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Workout : {}", id);
        workoutRepository.deleteById(id);
    }
}
