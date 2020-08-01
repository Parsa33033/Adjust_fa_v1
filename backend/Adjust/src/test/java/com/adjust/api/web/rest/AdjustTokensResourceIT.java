package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustTokens;
import com.adjust.api.repository.AdjustTokensRepository;
import com.adjust.api.service.AdjustTokensService;
import com.adjust.api.service.dto.AdjustTokensDTO;
import com.adjust.api.service.mapper.AdjustTokensMapper;

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
 * Integration tests for the {@link AdjustTokensResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustTokensResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_TOKEN = 1D;
    private static final Double UPDATED_TOKEN = 2D;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private AdjustTokensRepository adjustTokensRepository;

    @Autowired
    private AdjustTokensMapper adjustTokensMapper;

    @Autowired
    private AdjustTokensService adjustTokensService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustTokensMockMvc;

    private AdjustTokens adjustTokens;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustTokens createEntity(EntityManager em) {
        AdjustTokens adjustTokens = new AdjustTokens()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .token(DEFAULT_TOKEN)
            .price(DEFAULT_PRICE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return adjustTokens;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustTokens createUpdatedEntity(EntityManager em) {
        AdjustTokens adjustTokens = new AdjustTokens()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return adjustTokens;
    }

    @BeforeEach
    public void initTest() {
        adjustTokens = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustTokens() throws Exception {
        int databaseSizeBeforeCreate = adjustTokensRepository.findAll().size();
        // Create the AdjustTokens
        AdjustTokensDTO adjustTokensDTO = adjustTokensMapper.toDto(adjustTokens);
        restAdjustTokensMockMvc.perform(post("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokensDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustTokens in the database
        List<AdjustTokens> adjustTokensList = adjustTokensRepository.findAll();
        assertThat(adjustTokensList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustTokens testAdjustTokens = adjustTokensList.get(adjustTokensList.size() - 1);
        assertThat(testAdjustTokens.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdjustTokens.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAdjustTokens.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAdjustTokens.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testAdjustTokens.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testAdjustTokens.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAdjustTokensWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustTokensRepository.findAll().size();

        // Create the AdjustTokens with an existing ID
        adjustTokens.setId(1L);
        AdjustTokensDTO adjustTokensDTO = adjustTokensMapper.toDto(adjustTokens);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustTokensMockMvc.perform(post("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokensDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustTokens in the database
        List<AdjustTokens> adjustTokensList = adjustTokensRepository.findAll();
        assertThat(adjustTokensList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustTokens() throws Exception {
        // Initialize the database
        adjustTokensRepository.saveAndFlush(adjustTokens);

        // Get all the adjustTokensList
        restAdjustTokensMockMvc.perform(get("/api/adjust-tokens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustTokens.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getAdjustTokens() throws Exception {
        // Initialize the database
        adjustTokensRepository.saveAndFlush(adjustTokens);

        // Get the adjustTokens
        restAdjustTokensMockMvc.perform(get("/api/adjust-tokens/{id}", adjustTokens.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustTokens.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustTokens() throws Exception {
        // Get the adjustTokens
        restAdjustTokensMockMvc.perform(get("/api/adjust-tokens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustTokens() throws Exception {
        // Initialize the database
        adjustTokensRepository.saveAndFlush(adjustTokens);

        int databaseSizeBeforeUpdate = adjustTokensRepository.findAll().size();

        // Update the adjustTokens
        AdjustTokens updatedAdjustTokens = adjustTokensRepository.findById(adjustTokens.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustTokens are not directly saved in db
        em.detach(updatedAdjustTokens);
        updatedAdjustTokens
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        AdjustTokensDTO adjustTokensDTO = adjustTokensMapper.toDto(updatedAdjustTokens);

        restAdjustTokensMockMvc.perform(put("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokensDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustTokens in the database
        List<AdjustTokens> adjustTokensList = adjustTokensRepository.findAll();
        assertThat(adjustTokensList).hasSize(databaseSizeBeforeUpdate);
        AdjustTokens testAdjustTokens = adjustTokensList.get(adjustTokensList.size() - 1);
        assertThat(testAdjustTokens.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdjustTokens.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAdjustTokens.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAdjustTokens.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testAdjustTokens.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testAdjustTokens.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustTokens() throws Exception {
        int databaseSizeBeforeUpdate = adjustTokensRepository.findAll().size();

        // Create the AdjustTokens
        AdjustTokensDTO adjustTokensDTO = adjustTokensMapper.toDto(adjustTokens);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustTokensMockMvc.perform(put("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokensDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustTokens in the database
        List<AdjustTokens> adjustTokensList = adjustTokensRepository.findAll();
        assertThat(adjustTokensList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustTokens() throws Exception {
        // Initialize the database
        adjustTokensRepository.saveAndFlush(adjustTokens);

        int databaseSizeBeforeDelete = adjustTokensRepository.findAll().size();

        // Delete the adjustTokens
        restAdjustTokensMockMvc.perform(delete("/api/adjust-tokens/{id}", adjustTokens.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustTokens> adjustTokensList = adjustTokensRepository.findAll();
        assertThat(adjustTokensList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
