package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.NutritionProgram;
import com.adjust.api.repository.NutritionProgramRepository;
import com.adjust.api.service.NutritionProgramService;
import com.adjust.api.service.dto.NutritionProgramDTO;
import com.adjust.api.service.mapper.NutritionProgramMapper;

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
 * Integration tests for the {@link NutritionProgramResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NutritionProgramResourceIT {

    private static final Double DEFAULT_DAILY_CALORIES = 1D;
    private static final Double UPDATED_DAILY_CALORIES = 2D;

    private static final Integer DEFAULT_PROTEIN_PERCENTAGE = 1;
    private static final Integer UPDATED_PROTEIN_PERCENTAGE = 2;

    private static final Integer DEFAULT_CARBS_PERCENTAGE = 1;
    private static final Integer UPDATED_CARBS_PERCENTAGE = 2;

    private static final Integer DEFAULT_FAT_PERCENTAGE = 1;
    private static final Integer UPDATED_FAT_PERCENTAGE = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private NutritionProgramRepository nutritionProgramRepository;

    @Autowired
    private NutritionProgramMapper nutritionProgramMapper;

    @Autowired
    private NutritionProgramService nutritionProgramService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNutritionProgramMockMvc;

    private NutritionProgram nutritionProgram;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NutritionProgram createEntity(EntityManager em) {
        NutritionProgram nutritionProgram = new NutritionProgram()
            .dailyCalories(DEFAULT_DAILY_CALORIES)
            .proteinPercentage(DEFAULT_PROTEIN_PERCENTAGE)
            .carbsPercentage(DEFAULT_CARBS_PERCENTAGE)
            .fatPercentage(DEFAULT_FAT_PERCENTAGE)
            .description(DEFAULT_DESCRIPTION);
        return nutritionProgram;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NutritionProgram createUpdatedEntity(EntityManager em) {
        NutritionProgram nutritionProgram = new NutritionProgram()
            .dailyCalories(UPDATED_DAILY_CALORIES)
            .proteinPercentage(UPDATED_PROTEIN_PERCENTAGE)
            .carbsPercentage(UPDATED_CARBS_PERCENTAGE)
            .fatPercentage(UPDATED_FAT_PERCENTAGE)
            .description(UPDATED_DESCRIPTION);
        return nutritionProgram;
    }

    @BeforeEach
    public void initTest() {
        nutritionProgram = createEntity(em);
    }

    @Test
    @Transactional
    public void createNutritionProgram() throws Exception {
        int databaseSizeBeforeCreate = nutritionProgramRepository.findAll().size();
        // Create the NutritionProgram
        NutritionProgramDTO nutritionProgramDTO = nutritionProgramMapper.toDto(nutritionProgram);
        restNutritionProgramMockMvc.perform(post("/api/nutrition-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionProgramDTO)))
            .andExpect(status().isCreated());

        // Validate the NutritionProgram in the database
        List<NutritionProgram> nutritionProgramList = nutritionProgramRepository.findAll();
        assertThat(nutritionProgramList).hasSize(databaseSizeBeforeCreate + 1);
        NutritionProgram testNutritionProgram = nutritionProgramList.get(nutritionProgramList.size() - 1);
        assertThat(testNutritionProgram.getDailyCalories()).isEqualTo(DEFAULT_DAILY_CALORIES);
        assertThat(testNutritionProgram.getProteinPercentage()).isEqualTo(DEFAULT_PROTEIN_PERCENTAGE);
        assertThat(testNutritionProgram.getCarbsPercentage()).isEqualTo(DEFAULT_CARBS_PERCENTAGE);
        assertThat(testNutritionProgram.getFatPercentage()).isEqualTo(DEFAULT_FAT_PERCENTAGE);
        assertThat(testNutritionProgram.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createNutritionProgramWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nutritionProgramRepository.findAll().size();

        // Create the NutritionProgram with an existing ID
        nutritionProgram.setId(1L);
        NutritionProgramDTO nutritionProgramDTO = nutritionProgramMapper.toDto(nutritionProgram);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNutritionProgramMockMvc.perform(post("/api/nutrition-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NutritionProgram in the database
        List<NutritionProgram> nutritionProgramList = nutritionProgramRepository.findAll();
        assertThat(nutritionProgramList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNutritionPrograms() throws Exception {
        // Initialize the database
        nutritionProgramRepository.saveAndFlush(nutritionProgram);

        // Get all the nutritionProgramList
        restNutritionProgramMockMvc.perform(get("/api/nutrition-programs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nutritionProgram.getId().intValue())))
            .andExpect(jsonPath("$.[*].dailyCalories").value(hasItem(DEFAULT_DAILY_CALORIES.doubleValue())))
            .andExpect(jsonPath("$.[*].proteinPercentage").value(hasItem(DEFAULT_PROTEIN_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].carbsPercentage").value(hasItem(DEFAULT_CARBS_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].fatPercentage").value(hasItem(DEFAULT_FAT_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getNutritionProgram() throws Exception {
        // Initialize the database
        nutritionProgramRepository.saveAndFlush(nutritionProgram);

        // Get the nutritionProgram
        restNutritionProgramMockMvc.perform(get("/api/nutrition-programs/{id}", nutritionProgram.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nutritionProgram.getId().intValue()))
            .andExpect(jsonPath("$.dailyCalories").value(DEFAULT_DAILY_CALORIES.doubleValue()))
            .andExpect(jsonPath("$.proteinPercentage").value(DEFAULT_PROTEIN_PERCENTAGE))
            .andExpect(jsonPath("$.carbsPercentage").value(DEFAULT_CARBS_PERCENTAGE))
            .andExpect(jsonPath("$.fatPercentage").value(DEFAULT_FAT_PERCENTAGE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingNutritionProgram() throws Exception {
        // Get the nutritionProgram
        restNutritionProgramMockMvc.perform(get("/api/nutrition-programs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNutritionProgram() throws Exception {
        // Initialize the database
        nutritionProgramRepository.saveAndFlush(nutritionProgram);

        int databaseSizeBeforeUpdate = nutritionProgramRepository.findAll().size();

        // Update the nutritionProgram
        NutritionProgram updatedNutritionProgram = nutritionProgramRepository.findById(nutritionProgram.getId()).get();
        // Disconnect from session so that the updates on updatedNutritionProgram are not directly saved in db
        em.detach(updatedNutritionProgram);
        updatedNutritionProgram
            .dailyCalories(UPDATED_DAILY_CALORIES)
            .proteinPercentage(UPDATED_PROTEIN_PERCENTAGE)
            .carbsPercentage(UPDATED_CARBS_PERCENTAGE)
            .fatPercentage(UPDATED_FAT_PERCENTAGE)
            .description(UPDATED_DESCRIPTION);
        NutritionProgramDTO nutritionProgramDTO = nutritionProgramMapper.toDto(updatedNutritionProgram);

        restNutritionProgramMockMvc.perform(put("/api/nutrition-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionProgramDTO)))
            .andExpect(status().isOk());

        // Validate the NutritionProgram in the database
        List<NutritionProgram> nutritionProgramList = nutritionProgramRepository.findAll();
        assertThat(nutritionProgramList).hasSize(databaseSizeBeforeUpdate);
        NutritionProgram testNutritionProgram = nutritionProgramList.get(nutritionProgramList.size() - 1);
        assertThat(testNutritionProgram.getDailyCalories()).isEqualTo(UPDATED_DAILY_CALORIES);
        assertThat(testNutritionProgram.getProteinPercentage()).isEqualTo(UPDATED_PROTEIN_PERCENTAGE);
        assertThat(testNutritionProgram.getCarbsPercentage()).isEqualTo(UPDATED_CARBS_PERCENTAGE);
        assertThat(testNutritionProgram.getFatPercentage()).isEqualTo(UPDATED_FAT_PERCENTAGE);
        assertThat(testNutritionProgram.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNutritionProgram() throws Exception {
        int databaseSizeBeforeUpdate = nutritionProgramRepository.findAll().size();

        // Create the NutritionProgram
        NutritionProgramDTO nutritionProgramDTO = nutritionProgramMapper.toDto(nutritionProgram);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNutritionProgramMockMvc.perform(put("/api/nutrition-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NutritionProgram in the database
        List<NutritionProgram> nutritionProgramList = nutritionProgramRepository.findAll();
        assertThat(nutritionProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNutritionProgram() throws Exception {
        // Initialize the database
        nutritionProgramRepository.saveAndFlush(nutritionProgram);

        int databaseSizeBeforeDelete = nutritionProgramRepository.findAll().size();

        // Delete the nutritionProgram
        restNutritionProgramMockMvc.perform(delete("/api/nutrition-programs/{id}", nutritionProgram.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NutritionProgram> nutritionProgramList = nutritionProgramRepository.findAll();
        assertThat(nutritionProgramList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
