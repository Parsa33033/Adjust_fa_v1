package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Nutrition;
import com.adjust.api.repository.NutritionRepository;
import com.adjust.api.service.NutritionService;
import com.adjust.api.service.dto.NutritionDTO;
import com.adjust.api.service.mapper.NutritionMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NutritionResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NutritionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_UNIT = 1;
    private static final Integer UPDATED_UNIT = 2;

    private static final Long DEFAULT_ADJUST_NUTRITION_ID = 1L;
    private static final Long UPDATED_ADJUST_NUTRITION_ID = 2L;

    private static final Integer DEFAULT_CALORIES_PER_UNIT = 1;
    private static final Integer UPDATED_CALORIES_PER_UNIT = 2;

    private static final Integer DEFAULT_PROTEIN_PER_UNIT = 1;
    private static final Integer UPDATED_PROTEIN_PER_UNIT = 2;

    private static final Integer DEFAULT_CARBS_PER_UNIT = 1;
    private static final Integer UPDATED_CARBS_PER_UNIT = 2;

    private static final Integer DEFAULT_FAT_IN_UNIT = 1;
    private static final Integer UPDATED_FAT_IN_UNIT = 2;

    @Autowired
    private NutritionRepository nutritionRepository;

    @Autowired
    private NutritionMapper nutritionMapper;

    @Autowired
    private NutritionService nutritionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNutritionMockMvc;

    private Nutrition nutrition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nutrition createEntity(EntityManager em) {
        Nutrition nutrition = new Nutrition()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .unit(DEFAULT_UNIT)
            .adjustNutritionId(DEFAULT_ADJUST_NUTRITION_ID)
            .caloriesPerUnit(DEFAULT_CALORIES_PER_UNIT)
            .proteinPerUnit(DEFAULT_PROTEIN_PER_UNIT)
            .carbsPerUnit(DEFAULT_CARBS_PER_UNIT)
            .fatInUnit(DEFAULT_FAT_IN_UNIT);
        return nutrition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nutrition createUpdatedEntity(EntityManager em) {
        Nutrition nutrition = new Nutrition()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .unit(UPDATED_UNIT)
            .adjustNutritionId(UPDATED_ADJUST_NUTRITION_ID)
            .caloriesPerUnit(UPDATED_CALORIES_PER_UNIT)
            .proteinPerUnit(UPDATED_PROTEIN_PER_UNIT)
            .carbsPerUnit(UPDATED_CARBS_PER_UNIT)
            .fatInUnit(UPDATED_FAT_IN_UNIT);
        return nutrition;
    }

    @BeforeEach
    public void initTest() {
        nutrition = createEntity(em);
    }

    @Test
    @Transactional
    public void createNutrition() throws Exception {
        int databaseSizeBeforeCreate = nutritionRepository.findAll().size();
        // Create the Nutrition
        NutritionDTO nutritionDTO = nutritionMapper.toDto(nutrition);
        restNutritionMockMvc.perform(post("/api/nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionDTO)))
            .andExpect(status().isCreated());

        // Validate the Nutrition in the database
        List<Nutrition> nutritionList = nutritionRepository.findAll();
        assertThat(nutritionList).hasSize(databaseSizeBeforeCreate + 1);
        Nutrition testNutrition = nutritionList.get(nutritionList.size() - 1);
        assertThat(testNutrition.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNutrition.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNutrition.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testNutrition.getAdjustNutritionId()).isEqualTo(DEFAULT_ADJUST_NUTRITION_ID);
        assertThat(testNutrition.getCaloriesPerUnit()).isEqualTo(DEFAULT_CALORIES_PER_UNIT);
        assertThat(testNutrition.getProteinPerUnit()).isEqualTo(DEFAULT_PROTEIN_PER_UNIT);
        assertThat(testNutrition.getCarbsPerUnit()).isEqualTo(DEFAULT_CARBS_PER_UNIT);
        assertThat(testNutrition.getFatInUnit()).isEqualTo(DEFAULT_FAT_IN_UNIT);
    }

    @Test
    @Transactional
    public void createNutritionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nutritionRepository.findAll().size();

        // Create the Nutrition with an existing ID
        nutrition.setId(1L);
        NutritionDTO nutritionDTO = nutritionMapper.toDto(nutrition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNutritionMockMvc.perform(post("/api/nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nutrition in the database
        List<Nutrition> nutritionList = nutritionRepository.findAll();
        assertThat(nutritionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNutritions() throws Exception {
        // Initialize the database
        nutritionRepository.saveAndFlush(nutrition);

        // Get all the nutritionList
        restNutritionMockMvc.perform(get("/api/nutritions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nutrition.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].adjustNutritionId").value(hasItem(DEFAULT_ADJUST_NUTRITION_ID.intValue())))
            .andExpect(jsonPath("$.[*].caloriesPerUnit").value(hasItem(DEFAULT_CALORIES_PER_UNIT)))
            .andExpect(jsonPath("$.[*].proteinPerUnit").value(hasItem(DEFAULT_PROTEIN_PER_UNIT)))
            .andExpect(jsonPath("$.[*].carbsPerUnit").value(hasItem(DEFAULT_CARBS_PER_UNIT)))
            .andExpect(jsonPath("$.[*].fatInUnit").value(hasItem(DEFAULT_FAT_IN_UNIT)));
    }
    
    @Test
    @Transactional
    public void getNutrition() throws Exception {
        // Initialize the database
        nutritionRepository.saveAndFlush(nutrition);

        // Get the nutrition
        restNutritionMockMvc.perform(get("/api/nutritions/{id}", nutrition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nutrition.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.adjustNutritionId").value(DEFAULT_ADJUST_NUTRITION_ID.intValue()))
            .andExpect(jsonPath("$.caloriesPerUnit").value(DEFAULT_CALORIES_PER_UNIT))
            .andExpect(jsonPath("$.proteinPerUnit").value(DEFAULT_PROTEIN_PER_UNIT))
            .andExpect(jsonPath("$.carbsPerUnit").value(DEFAULT_CARBS_PER_UNIT))
            .andExpect(jsonPath("$.fatInUnit").value(DEFAULT_FAT_IN_UNIT));
    }
    @Test
    @Transactional
    public void getNonExistingNutrition() throws Exception {
        // Get the nutrition
        restNutritionMockMvc.perform(get("/api/nutritions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNutrition() throws Exception {
        // Initialize the database
        nutritionRepository.saveAndFlush(nutrition);

        int databaseSizeBeforeUpdate = nutritionRepository.findAll().size();

        // Update the nutrition
        Nutrition updatedNutrition = nutritionRepository.findById(nutrition.getId()).get();
        // Disconnect from session so that the updates on updatedNutrition are not directly saved in db
        em.detach(updatedNutrition);
        updatedNutrition
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .unit(UPDATED_UNIT)
            .adjustNutritionId(UPDATED_ADJUST_NUTRITION_ID)
            .caloriesPerUnit(UPDATED_CALORIES_PER_UNIT)
            .proteinPerUnit(UPDATED_PROTEIN_PER_UNIT)
            .carbsPerUnit(UPDATED_CARBS_PER_UNIT)
            .fatInUnit(UPDATED_FAT_IN_UNIT);
        NutritionDTO nutritionDTO = nutritionMapper.toDto(updatedNutrition);

        restNutritionMockMvc.perform(put("/api/nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionDTO)))
            .andExpect(status().isOk());

        // Validate the Nutrition in the database
        List<Nutrition> nutritionList = nutritionRepository.findAll();
        assertThat(nutritionList).hasSize(databaseSizeBeforeUpdate);
        Nutrition testNutrition = nutritionList.get(nutritionList.size() - 1);
        assertThat(testNutrition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNutrition.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNutrition.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testNutrition.getAdjustNutritionId()).isEqualTo(UPDATED_ADJUST_NUTRITION_ID);
        assertThat(testNutrition.getCaloriesPerUnit()).isEqualTo(UPDATED_CALORIES_PER_UNIT);
        assertThat(testNutrition.getProteinPerUnit()).isEqualTo(UPDATED_PROTEIN_PER_UNIT);
        assertThat(testNutrition.getCarbsPerUnit()).isEqualTo(UPDATED_CARBS_PER_UNIT);
        assertThat(testNutrition.getFatInUnit()).isEqualTo(UPDATED_FAT_IN_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingNutrition() throws Exception {
        int databaseSizeBeforeUpdate = nutritionRepository.findAll().size();

        // Create the Nutrition
        NutritionDTO nutritionDTO = nutritionMapper.toDto(nutrition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNutritionMockMvc.perform(put("/api/nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutritionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nutrition in the database
        List<Nutrition> nutritionList = nutritionRepository.findAll();
        assertThat(nutritionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNutrition() throws Exception {
        // Initialize the database
        nutritionRepository.saveAndFlush(nutrition);

        int databaseSizeBeforeDelete = nutritionRepository.findAll().size();

        // Delete the nutrition
        restNutritionMockMvc.perform(delete("/api/nutritions/{id}", nutrition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nutrition> nutritionList = nutritionRepository.findAll();
        assertThat(nutritionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
