package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustFood;
import com.adjust.api.repository.AdjustFoodRepository;
import com.adjust.api.service.AdjustFoodService;
import com.adjust.api.service.dto.AdjustFoodDTO;
import com.adjust.api.service.mapper.AdjustFoodMapper;

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
 * Integration tests for the {@link AdjustFoodResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustFoodResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AdjustFoodRepository adjustFoodRepository;

    @Autowired
    private AdjustFoodMapper adjustFoodMapper;

    @Autowired
    private AdjustFoodService adjustFoodService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustFoodMockMvc;

    private AdjustFood adjustFood;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustFood createEntity(EntityManager em) {
        AdjustFood adjustFood = new AdjustFood()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return adjustFood;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustFood createUpdatedEntity(EntityManager em) {
        AdjustFood adjustFood = new AdjustFood()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return adjustFood;
    }

    @BeforeEach
    public void initTest() {
        adjustFood = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustFood() throws Exception {
        int databaseSizeBeforeCreate = adjustFoodRepository.findAll().size();
        // Create the AdjustFood
        AdjustFoodDTO adjustFoodDTO = adjustFoodMapper.toDto(adjustFood);
        restAdjustFoodMockMvc.perform(post("/api/adjust-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustFoodDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustFood in the database
        List<AdjustFood> adjustFoodList = adjustFoodRepository.findAll();
        assertThat(adjustFoodList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustFood testAdjustFood = adjustFoodList.get(adjustFoodList.size() - 1);
        assertThat(testAdjustFood.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdjustFood.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAdjustFoodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustFoodRepository.findAll().size();

        // Create the AdjustFood with an existing ID
        adjustFood.setId(1L);
        AdjustFoodDTO adjustFoodDTO = adjustFoodMapper.toDto(adjustFood);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustFoodMockMvc.perform(post("/api/adjust-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustFoodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustFood in the database
        List<AdjustFood> adjustFoodList = adjustFoodRepository.findAll();
        assertThat(adjustFoodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustFoods() throws Exception {
        // Initialize the database
        adjustFoodRepository.saveAndFlush(adjustFood);

        // Get all the adjustFoodList
        restAdjustFoodMockMvc.perform(get("/api/adjust-foods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustFood.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getAdjustFood() throws Exception {
        // Initialize the database
        adjustFoodRepository.saveAndFlush(adjustFood);

        // Get the adjustFood
        restAdjustFoodMockMvc.perform(get("/api/adjust-foods/{id}", adjustFood.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustFood.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustFood() throws Exception {
        // Get the adjustFood
        restAdjustFoodMockMvc.perform(get("/api/adjust-foods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustFood() throws Exception {
        // Initialize the database
        adjustFoodRepository.saveAndFlush(adjustFood);

        int databaseSizeBeforeUpdate = adjustFoodRepository.findAll().size();

        // Update the adjustFood
        AdjustFood updatedAdjustFood = adjustFoodRepository.findById(adjustFood.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustFood are not directly saved in db
        em.detach(updatedAdjustFood);
        updatedAdjustFood
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        AdjustFoodDTO adjustFoodDTO = adjustFoodMapper.toDto(updatedAdjustFood);

        restAdjustFoodMockMvc.perform(put("/api/adjust-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustFoodDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustFood in the database
        List<AdjustFood> adjustFoodList = adjustFoodRepository.findAll();
        assertThat(adjustFoodList).hasSize(databaseSizeBeforeUpdate);
        AdjustFood testAdjustFood = adjustFoodList.get(adjustFoodList.size() - 1);
        assertThat(testAdjustFood.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdjustFood.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustFood() throws Exception {
        int databaseSizeBeforeUpdate = adjustFoodRepository.findAll().size();

        // Create the AdjustFood
        AdjustFoodDTO adjustFoodDTO = adjustFoodMapper.toDto(adjustFood);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustFoodMockMvc.perform(put("/api/adjust-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustFoodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustFood in the database
        List<AdjustFood> adjustFoodList = adjustFoodRepository.findAll();
        assertThat(adjustFoodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustFood() throws Exception {
        // Initialize the database
        adjustFoodRepository.saveAndFlush(adjustFood);

        int databaseSizeBeforeDelete = adjustFoodRepository.findAll().size();

        // Delete the adjustFood
        restAdjustFoodMockMvc.perform(delete("/api/adjust-foods/{id}", adjustFood.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustFood> adjustFoodList = adjustFoodRepository.findAll();
        assertThat(adjustFoodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
