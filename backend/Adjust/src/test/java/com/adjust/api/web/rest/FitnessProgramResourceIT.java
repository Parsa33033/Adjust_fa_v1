package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.FitnessProgram;
import com.adjust.api.repository.FitnessProgramRepository;
import com.adjust.api.service.FitnessProgramService;
import com.adjust.api.service.dto.FitnessProgramDTO;
import com.adjust.api.service.mapper.FitnessProgramMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FitnessProgramResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FitnessProgramResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private FitnessProgramRepository fitnessProgramRepository;

    @Autowired
    private FitnessProgramMapper fitnessProgramMapper;

    @Autowired
    private FitnessProgramService fitnessProgramService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFitnessProgramMockMvc;

    private FitnessProgram fitnessProgram;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FitnessProgram createEntity(EntityManager em) {
        FitnessProgram fitnessProgram = new FitnessProgram()
            .type(DEFAULT_TYPE)
            .description(DEFAULT_DESCRIPTION);
        return fitnessProgram;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FitnessProgram createUpdatedEntity(EntityManager em) {
        FitnessProgram fitnessProgram = new FitnessProgram()
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION);
        return fitnessProgram;
    }

    @BeforeEach
    public void initTest() {
        fitnessProgram = createEntity(em);
    }

    @Test
    @Transactional
    public void createFitnessProgram() throws Exception {
        int databaseSizeBeforeCreate = fitnessProgramRepository.findAll().size();
        // Create the FitnessProgram
        FitnessProgramDTO fitnessProgramDTO = fitnessProgramMapper.toDto(fitnessProgram);
        restFitnessProgramMockMvc.perform(post("/api/fitness-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fitnessProgramDTO)))
            .andExpect(status().isCreated());

        // Validate the FitnessProgram in the database
        List<FitnessProgram> fitnessProgramList = fitnessProgramRepository.findAll();
        assertThat(fitnessProgramList).hasSize(databaseSizeBeforeCreate + 1);
        FitnessProgram testFitnessProgram = fitnessProgramList.get(fitnessProgramList.size() - 1);
        assertThat(testFitnessProgram.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFitnessProgram.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createFitnessProgramWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fitnessProgramRepository.findAll().size();

        // Create the FitnessProgram with an existing ID
        fitnessProgram.setId(1L);
        FitnessProgramDTO fitnessProgramDTO = fitnessProgramMapper.toDto(fitnessProgram);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFitnessProgramMockMvc.perform(post("/api/fitness-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fitnessProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FitnessProgram in the database
        List<FitnessProgram> fitnessProgramList = fitnessProgramRepository.findAll();
        assertThat(fitnessProgramList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFitnessPrograms() throws Exception {
        // Initialize the database
        fitnessProgramRepository.saveAndFlush(fitnessProgram);

        // Get all the fitnessProgramList
        restFitnessProgramMockMvc.perform(get("/api/fitness-programs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fitnessProgram.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getFitnessProgram() throws Exception {
        // Initialize the database
        fitnessProgramRepository.saveAndFlush(fitnessProgram);

        // Get the fitnessProgram
        restFitnessProgramMockMvc.perform(get("/api/fitness-programs/{id}", fitnessProgram.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fitnessProgram.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFitnessProgram() throws Exception {
        // Get the fitnessProgram
        restFitnessProgramMockMvc.perform(get("/api/fitness-programs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFitnessProgram() throws Exception {
        // Initialize the database
        fitnessProgramRepository.saveAndFlush(fitnessProgram);

        int databaseSizeBeforeUpdate = fitnessProgramRepository.findAll().size();

        // Update the fitnessProgram
        FitnessProgram updatedFitnessProgram = fitnessProgramRepository.findById(fitnessProgram.getId()).get();
        // Disconnect from session so that the updates on updatedFitnessProgram are not directly saved in db
        em.detach(updatedFitnessProgram);
        updatedFitnessProgram
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION);
        FitnessProgramDTO fitnessProgramDTO = fitnessProgramMapper.toDto(updatedFitnessProgram);

        restFitnessProgramMockMvc.perform(put("/api/fitness-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fitnessProgramDTO)))
            .andExpect(status().isOk());

        // Validate the FitnessProgram in the database
        List<FitnessProgram> fitnessProgramList = fitnessProgramRepository.findAll();
        assertThat(fitnessProgramList).hasSize(databaseSizeBeforeUpdate);
        FitnessProgram testFitnessProgram = fitnessProgramList.get(fitnessProgramList.size() - 1);
        assertThat(testFitnessProgram.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFitnessProgram.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingFitnessProgram() throws Exception {
        int databaseSizeBeforeUpdate = fitnessProgramRepository.findAll().size();

        // Create the FitnessProgram
        FitnessProgramDTO fitnessProgramDTO = fitnessProgramMapper.toDto(fitnessProgram);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFitnessProgramMockMvc.perform(put("/api/fitness-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fitnessProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FitnessProgram in the database
        List<FitnessProgram> fitnessProgramList = fitnessProgramRepository.findAll();
        assertThat(fitnessProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFitnessProgram() throws Exception {
        // Initialize the database
        fitnessProgramRepository.saveAndFlush(fitnessProgram);

        int databaseSizeBeforeDelete = fitnessProgramRepository.findAll().size();

        // Delete the fitnessProgram
        restFitnessProgramMockMvc.perform(delete("/api/fitness-programs/{id}", fitnessProgram.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FitnessProgram> fitnessProgramList = fitnessProgramRepository.findAll();
        assertThat(fitnessProgramList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
