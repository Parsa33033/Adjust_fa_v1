package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustNutrition;
import com.adjust.api.repository.AdjustNutritionRepository;
import com.adjust.api.service.AdjustNutritionService;
import com.adjust.api.service.dto.AdjustNutritionDTO;
import com.adjust.api.service.mapper.AdjustNutritionMapper;

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
 * Integration tests for the {@link AdjustNutritionResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustNutritionResourceIT {

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
    private AdjustNutritionRepository adjustNutritionRepository;

    @Autowired
    private AdjustNutritionMapper adjustNutritionMapper;

    @Autowired
    private AdjustNutritionService adjustNutritionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustNutritionMockMvc;

    private AdjustNutrition adjustNutrition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustNutrition createEntity(EntityManager em) {
        AdjustNutrition adjustNutrition = new AdjustNutrition()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .unit(DEFAULT_UNIT)
            .adjustNutritionId(DEFAULT_ADJUST_NUTRITION_ID)
            .caloriesPerUnit(DEFAULT_CALORIES_PER_UNIT)
            .proteinPerUnit(DEFAULT_PROTEIN_PER_UNIT)
            .carbsPerUnit(DEFAULT_CARBS_PER_UNIT)
            .fatInUnit(DEFAULT_FAT_IN_UNIT);
        return adjustNutrition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustNutrition createUpdatedEntity(EntityManager em) {
        AdjustNutrition adjustNutrition = new AdjustNutrition()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .unit(UPDATED_UNIT)
            .adjustNutritionId(UPDATED_ADJUST_NUTRITION_ID)
            .caloriesPerUnit(UPDATED_CALORIES_PER_UNIT)
            .proteinPerUnit(UPDATED_PROTEIN_PER_UNIT)
            .carbsPerUnit(UPDATED_CARBS_PER_UNIT)
            .fatInUnit(UPDATED_FAT_IN_UNIT);
        return adjustNutrition;
    }

    @BeforeEach
    public void initTest() {
        adjustNutrition = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustNutrition() throws Exception {
        int databaseSizeBeforeCreate = adjustNutritionRepository.findAll().size();
        // Create the AdjustNutrition
        AdjustNutritionDTO adjustNutritionDTO = adjustNutritionMapper.toDto(adjustNutrition);
        restAdjustNutritionMockMvc.perform(post("/api/adjust-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustNutritionDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustNutrition in the database
        List<AdjustNutrition> adjustNutritionList = adjustNutritionRepository.findAll();
        assertThat(adjustNutritionList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustNutrition testAdjustNutrition = adjustNutritionList.get(adjustNutritionList.size() - 1);
        assertThat(testAdjustNutrition.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdjustNutrition.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAdjustNutrition.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testAdjustNutrition.getAdjustNutritionId()).isEqualTo(DEFAULT_ADJUST_NUTRITION_ID);
        assertThat(testAdjustNutrition.getCaloriesPerUnit()).isEqualTo(DEFAULT_CALORIES_PER_UNIT);
        assertThat(testAdjustNutrition.getProteinPerUnit()).isEqualTo(DEFAULT_PROTEIN_PER_UNIT);
        assertThat(testAdjustNutrition.getCarbsPerUnit()).isEqualTo(DEFAULT_CARBS_PER_UNIT);
        assertThat(testAdjustNutrition.getFatInUnit()).isEqualTo(DEFAULT_FAT_IN_UNIT);
    }

    @Test
    @Transactional
    public void createAdjustNutritionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustNutritionRepository.findAll().size();

        // Create the AdjustNutrition with an existing ID
        adjustNutrition.setId(1L);
        AdjustNutritionDTO adjustNutritionDTO = adjustNutritionMapper.toDto(adjustNutrition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustNutritionMockMvc.perform(post("/api/adjust-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustNutritionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustNutrition in the database
        List<AdjustNutrition> adjustNutritionList = adjustNutritionRepository.findAll();
        assertThat(adjustNutritionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustNutritions() throws Exception {
        // Initialize the database
        adjustNutritionRepository.saveAndFlush(adjustNutrition);

        // Get all the adjustNutritionList
        restAdjustNutritionMockMvc.perform(get("/api/adjust-nutritions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustNutrition.getId().intValue())))
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
    public void getAdjustNutrition() throws Exception {
        // Initialize the database
        adjustNutritionRepository.saveAndFlush(adjustNutrition);

        // Get the adjustNutrition
        restAdjustNutritionMockMvc.perform(get("/api/adjust-nutritions/{id}", adjustNutrition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustNutrition.getId().intValue()))
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
    public void getNonExistingAdjustNutrition() throws Exception {
        // Get the adjustNutrition
        restAdjustNutritionMockMvc.perform(get("/api/adjust-nutritions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustNutrition() throws Exception {
        // Initialize the database
        adjustNutritionRepository.saveAndFlush(adjustNutrition);

        int databaseSizeBeforeUpdate = adjustNutritionRepository.findAll().size();

        // Update the adjustNutrition
        AdjustNutrition updatedAdjustNutrition = adjustNutritionRepository.findById(adjustNutrition.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustNutrition are not directly saved in db
        em.detach(updatedAdjustNutrition);
        updatedAdjustNutrition
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .unit(UPDATED_UNIT)
            .adjustNutritionId(UPDATED_ADJUST_NUTRITION_ID)
            .caloriesPerUnit(UPDATED_CALORIES_PER_UNIT)
            .proteinPerUnit(UPDATED_PROTEIN_PER_UNIT)
            .carbsPerUnit(UPDATED_CARBS_PER_UNIT)
            .fatInUnit(UPDATED_FAT_IN_UNIT);
        AdjustNutritionDTO adjustNutritionDTO = adjustNutritionMapper.toDto(updatedAdjustNutrition);

        restAdjustNutritionMockMvc.perform(put("/api/adjust-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustNutritionDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustNutrition in the database
        List<AdjustNutrition> adjustNutritionList = adjustNutritionRepository.findAll();
        assertThat(adjustNutritionList).hasSize(databaseSizeBeforeUpdate);
        AdjustNutrition testAdjustNutrition = adjustNutritionList.get(adjustNutritionList.size() - 1);
        assertThat(testAdjustNutrition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdjustNutrition.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAdjustNutrition.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testAdjustNutrition.getAdjustNutritionId()).isEqualTo(UPDATED_ADJUST_NUTRITION_ID);
        assertThat(testAdjustNutrition.getCaloriesPerUnit()).isEqualTo(UPDATED_CALORIES_PER_UNIT);
        assertThat(testAdjustNutrition.getProteinPerUnit()).isEqualTo(UPDATED_PROTEIN_PER_UNIT);
        assertThat(testAdjustNutrition.getCarbsPerUnit()).isEqualTo(UPDATED_CARBS_PER_UNIT);
        assertThat(testAdjustNutrition.getFatInUnit()).isEqualTo(UPDATED_FAT_IN_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustNutrition() throws Exception {
        int databaseSizeBeforeUpdate = adjustNutritionRepository.findAll().size();

        // Create the AdjustNutrition
        AdjustNutritionDTO adjustNutritionDTO = adjustNutritionMapper.toDto(adjustNutrition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustNutritionMockMvc.perform(put("/api/adjust-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustNutritionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustNutrition in the database
        List<AdjustNutrition> adjustNutritionList = adjustNutritionRepository.findAll();
        assertThat(adjustNutritionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustNutrition() throws Exception {
        // Initialize the database
        adjustNutritionRepository.saveAndFlush(adjustNutrition);

        int databaseSizeBeforeDelete = adjustNutritionRepository.findAll().size();

        // Delete the adjustNutrition
        restAdjustNutritionMockMvc.perform(delete("/api/adjust-nutritions/{id}", adjustNutrition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustNutrition> adjustNutritionList = adjustNutritionRepository.findAll();
        assertThat(adjustNutritionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
