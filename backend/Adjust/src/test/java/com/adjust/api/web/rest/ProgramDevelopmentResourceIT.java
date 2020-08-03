package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.ProgramDevelopment;
import com.adjust.api.repository.ProgramDevelopmentRepository;
import com.adjust.api.service.ProgramDevelopmentService;
import com.adjust.api.service.dto.ProgramDevelopmentDTO;
import com.adjust.api.service.mapper.ProgramDevelopmentMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProgramDevelopmentResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProgramDevelopmentResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_WORKOUT_SCORE = 1D;
    private static final Double UPDATED_WORKOUT_SCORE = 2D;

    private static final Double DEFAULT_FITNESS_SCORE = 1D;
    private static final Double UPDATED_FITNESS_SCORE = 2D;

    @Autowired
    private ProgramDevelopmentRepository programDevelopmentRepository;

    @Autowired
    private ProgramDevelopmentMapper programDevelopmentMapper;

    @Autowired
    private ProgramDevelopmentService programDevelopmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgramDevelopmentMockMvc;

    private ProgramDevelopment programDevelopment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgramDevelopment createEntity(EntityManager em) {
        ProgramDevelopment programDevelopment = new ProgramDevelopment()
            .date(DEFAULT_DATE)
            .workoutScore(DEFAULT_WORKOUT_SCORE)
            .fitnessScore(DEFAULT_FITNESS_SCORE);
        return programDevelopment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgramDevelopment createUpdatedEntity(EntityManager em) {
        ProgramDevelopment programDevelopment = new ProgramDevelopment()
            .date(UPDATED_DATE)
            .workoutScore(UPDATED_WORKOUT_SCORE)
            .fitnessScore(UPDATED_FITNESS_SCORE);
        return programDevelopment;
    }

    @BeforeEach
    public void initTest() {
        programDevelopment = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgramDevelopment() throws Exception {
        int databaseSizeBeforeCreate = programDevelopmentRepository.findAll().size();
        // Create the ProgramDevelopment
        ProgramDevelopmentDTO programDevelopmentDTO = programDevelopmentMapper.toDto(programDevelopment);
        restProgramDevelopmentMockMvc.perform(post("/api/program-developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDevelopmentDTO)))
            .andExpect(status().isCreated());

        // Validate the ProgramDevelopment in the database
        List<ProgramDevelopment> programDevelopmentList = programDevelopmentRepository.findAll();
        assertThat(programDevelopmentList).hasSize(databaseSizeBeforeCreate + 1);
        ProgramDevelopment testProgramDevelopment = programDevelopmentList.get(programDevelopmentList.size() - 1);
        assertThat(testProgramDevelopment.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testProgramDevelopment.getWorkoutScore()).isEqualTo(DEFAULT_WORKOUT_SCORE);
        assertThat(testProgramDevelopment.getFitnessScore()).isEqualTo(DEFAULT_FITNESS_SCORE);
    }

    @Test
    @Transactional
    public void createProgramDevelopmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programDevelopmentRepository.findAll().size();

        // Create the ProgramDevelopment with an existing ID
        programDevelopment.setId(1L);
        ProgramDevelopmentDTO programDevelopmentDTO = programDevelopmentMapper.toDto(programDevelopment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramDevelopmentMockMvc.perform(post("/api/program-developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDevelopmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProgramDevelopment in the database
        List<ProgramDevelopment> programDevelopmentList = programDevelopmentRepository.findAll();
        assertThat(programDevelopmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProgramDevelopments() throws Exception {
        // Initialize the database
        programDevelopmentRepository.saveAndFlush(programDevelopment);

        // Get all the programDevelopmentList
        restProgramDevelopmentMockMvc.perform(get("/api/program-developments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programDevelopment.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].workoutScore").value(hasItem(DEFAULT_WORKOUT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].fitnessScore").value(hasItem(DEFAULT_FITNESS_SCORE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getProgramDevelopment() throws Exception {
        // Initialize the database
        programDevelopmentRepository.saveAndFlush(programDevelopment);

        // Get the programDevelopment
        restProgramDevelopmentMockMvc.perform(get("/api/program-developments/{id}", programDevelopment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(programDevelopment.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.workoutScore").value(DEFAULT_WORKOUT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.fitnessScore").value(DEFAULT_FITNESS_SCORE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProgramDevelopment() throws Exception {
        // Get the programDevelopment
        restProgramDevelopmentMockMvc.perform(get("/api/program-developments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgramDevelopment() throws Exception {
        // Initialize the database
        programDevelopmentRepository.saveAndFlush(programDevelopment);

        int databaseSizeBeforeUpdate = programDevelopmentRepository.findAll().size();

        // Update the programDevelopment
        ProgramDevelopment updatedProgramDevelopment = programDevelopmentRepository.findById(programDevelopment.getId()).get();
        // Disconnect from session so that the updates on updatedProgramDevelopment are not directly saved in db
        em.detach(updatedProgramDevelopment);
        updatedProgramDevelopment
            .date(UPDATED_DATE)
            .workoutScore(UPDATED_WORKOUT_SCORE)
            .fitnessScore(UPDATED_FITNESS_SCORE);
        ProgramDevelopmentDTO programDevelopmentDTO = programDevelopmentMapper.toDto(updatedProgramDevelopment);

        restProgramDevelopmentMockMvc.perform(put("/api/program-developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDevelopmentDTO)))
            .andExpect(status().isOk());

        // Validate the ProgramDevelopment in the database
        List<ProgramDevelopment> programDevelopmentList = programDevelopmentRepository.findAll();
        assertThat(programDevelopmentList).hasSize(databaseSizeBeforeUpdate);
        ProgramDevelopment testProgramDevelopment = programDevelopmentList.get(programDevelopmentList.size() - 1);
        assertThat(testProgramDevelopment.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testProgramDevelopment.getWorkoutScore()).isEqualTo(UPDATED_WORKOUT_SCORE);
        assertThat(testProgramDevelopment.getFitnessScore()).isEqualTo(UPDATED_FITNESS_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingProgramDevelopment() throws Exception {
        int databaseSizeBeforeUpdate = programDevelopmentRepository.findAll().size();

        // Create the ProgramDevelopment
        ProgramDevelopmentDTO programDevelopmentDTO = programDevelopmentMapper.toDto(programDevelopment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgramDevelopmentMockMvc.perform(put("/api/program-developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDevelopmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProgramDevelopment in the database
        List<ProgramDevelopment> programDevelopmentList = programDevelopmentRepository.findAll();
        assertThat(programDevelopmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProgramDevelopment() throws Exception {
        // Initialize the database
        programDevelopmentRepository.saveAndFlush(programDevelopment);

        int databaseSizeBeforeDelete = programDevelopmentRepository.findAll().size();

        // Delete the programDevelopment
        restProgramDevelopmentMockMvc.perform(delete("/api/program-developments/{id}", programDevelopment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProgramDevelopment> programDevelopmentList = programDevelopmentRepository.findAll();
        assertThat(programDevelopmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
