package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustTutorial;
import com.adjust.api.repository.AdjustTutorialRepository;
import com.adjust.api.service.AdjustTutorialService;
import com.adjust.api.service.dto.AdjustTutorialDTO;
import com.adjust.api.service.mapper.AdjustTutorialMapper;

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
 * Integration tests for the {@link AdjustTutorialResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustTutorialResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_THUMBNAIL = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_THUMBNAIL = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_THUMBNAIL_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_THUMBNAIL_CONTENT_TYPE = "image/png";

    private static final Double DEFAULT_TOKEN_PRICE = 1D;
    private static final Double UPDATED_TOKEN_PRICE = 2D;

    @Autowired
    private AdjustTutorialRepository adjustTutorialRepository;

    @Autowired
    private AdjustTutorialMapper adjustTutorialMapper;

    @Autowired
    private AdjustTutorialService adjustTutorialService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustTutorialMockMvc;

    private AdjustTutorial adjustTutorial;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustTutorial createEntity(EntityManager em) {
        AdjustTutorial adjustTutorial = new AdjustTutorial()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .text(DEFAULT_TEXT)
            .thumbnail(DEFAULT_THUMBNAIL)
            .thumbnailContentType(DEFAULT_THUMBNAIL_CONTENT_TYPE)
            .tokenPrice(DEFAULT_TOKEN_PRICE);
        return adjustTutorial;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustTutorial createUpdatedEntity(EntityManager em) {
        AdjustTutorial adjustTutorial = new AdjustTutorial()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .thumbnail(UPDATED_THUMBNAIL)
            .thumbnailContentType(UPDATED_THUMBNAIL_CONTENT_TYPE)
            .tokenPrice(UPDATED_TOKEN_PRICE);
        return adjustTutorial;
    }

    @BeforeEach
    public void initTest() {
        adjustTutorial = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustTutorial() throws Exception {
        int databaseSizeBeforeCreate = adjustTutorialRepository.findAll().size();
        // Create the AdjustTutorial
        AdjustTutorialDTO adjustTutorialDTO = adjustTutorialMapper.toDto(adjustTutorial);
        restAdjustTutorialMockMvc.perform(post("/api/adjust-tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustTutorial in the database
        List<AdjustTutorial> adjustTutorialList = adjustTutorialRepository.findAll();
        assertThat(adjustTutorialList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustTutorial testAdjustTutorial = adjustTutorialList.get(adjustTutorialList.size() - 1);
        assertThat(testAdjustTutorial.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAdjustTutorial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAdjustTutorial.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testAdjustTutorial.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testAdjustTutorial.getThumbnailContentType()).isEqualTo(DEFAULT_THUMBNAIL_CONTENT_TYPE);
        assertThat(testAdjustTutorial.getTokenPrice()).isEqualTo(DEFAULT_TOKEN_PRICE);
    }

    @Test
    @Transactional
    public void createAdjustTutorialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustTutorialRepository.findAll().size();

        // Create the AdjustTutorial with an existing ID
        adjustTutorial.setId(1L);
        AdjustTutorialDTO adjustTutorialDTO = adjustTutorialMapper.toDto(adjustTutorial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustTutorialMockMvc.perform(post("/api/adjust-tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustTutorial in the database
        List<AdjustTutorial> adjustTutorialList = adjustTutorialRepository.findAll();
        assertThat(adjustTutorialList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustTutorials() throws Exception {
        // Initialize the database
        adjustTutorialRepository.saveAndFlush(adjustTutorial);

        // Get all the adjustTutorialList
        restAdjustTutorialMockMvc.perform(get("/api/adjust-tutorials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustTutorial.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].thumbnailContentType").value(hasItem(DEFAULT_THUMBNAIL_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].thumbnail").value(hasItem(Base64Utils.encodeToString(DEFAULT_THUMBNAIL))))
            .andExpect(jsonPath("$.[*].tokenPrice").value(hasItem(DEFAULT_TOKEN_PRICE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getAdjustTutorial() throws Exception {
        // Initialize the database
        adjustTutorialRepository.saveAndFlush(adjustTutorial);

        // Get the adjustTutorial
        restAdjustTutorialMockMvc.perform(get("/api/adjust-tutorials/{id}", adjustTutorial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustTutorial.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.thumbnailContentType").value(DEFAULT_THUMBNAIL_CONTENT_TYPE))
            .andExpect(jsonPath("$.thumbnail").value(Base64Utils.encodeToString(DEFAULT_THUMBNAIL)))
            .andExpect(jsonPath("$.tokenPrice").value(DEFAULT_TOKEN_PRICE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustTutorial() throws Exception {
        // Get the adjustTutorial
        restAdjustTutorialMockMvc.perform(get("/api/adjust-tutorials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustTutorial() throws Exception {
        // Initialize the database
        adjustTutorialRepository.saveAndFlush(adjustTutorial);

        int databaseSizeBeforeUpdate = adjustTutorialRepository.findAll().size();

        // Update the adjustTutorial
        AdjustTutorial updatedAdjustTutorial = adjustTutorialRepository.findById(adjustTutorial.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustTutorial are not directly saved in db
        em.detach(updatedAdjustTutorial);
        updatedAdjustTutorial
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .thumbnail(UPDATED_THUMBNAIL)
            .thumbnailContentType(UPDATED_THUMBNAIL_CONTENT_TYPE)
            .tokenPrice(UPDATED_TOKEN_PRICE);
        AdjustTutorialDTO adjustTutorialDTO = adjustTutorialMapper.toDto(updatedAdjustTutorial);

        restAdjustTutorialMockMvc.perform(put("/api/adjust-tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustTutorial in the database
        List<AdjustTutorial> adjustTutorialList = adjustTutorialRepository.findAll();
        assertThat(adjustTutorialList).hasSize(databaseSizeBeforeUpdate);
        AdjustTutorial testAdjustTutorial = adjustTutorialList.get(adjustTutorialList.size() - 1);
        assertThat(testAdjustTutorial.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAdjustTutorial.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAdjustTutorial.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testAdjustTutorial.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testAdjustTutorial.getThumbnailContentType()).isEqualTo(UPDATED_THUMBNAIL_CONTENT_TYPE);
        assertThat(testAdjustTutorial.getTokenPrice()).isEqualTo(UPDATED_TOKEN_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustTutorial() throws Exception {
        int databaseSizeBeforeUpdate = adjustTutorialRepository.findAll().size();

        // Create the AdjustTutorial
        AdjustTutorialDTO adjustTutorialDTO = adjustTutorialMapper.toDto(adjustTutorial);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustTutorialMockMvc.perform(put("/api/adjust-tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustTutorial in the database
        List<AdjustTutorial> adjustTutorialList = adjustTutorialRepository.findAll();
        assertThat(adjustTutorialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustTutorial() throws Exception {
        // Initialize the database
        adjustTutorialRepository.saveAndFlush(adjustTutorial);

        int databaseSizeBeforeDelete = adjustTutorialRepository.findAll().size();

        // Delete the adjustTutorial
        restAdjustTutorialMockMvc.perform(delete("/api/adjust-tutorials/{id}", adjustTutorial.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustTutorial> adjustTutorialList = adjustTutorialRepository.findAll();
        assertThat(adjustTutorialList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
