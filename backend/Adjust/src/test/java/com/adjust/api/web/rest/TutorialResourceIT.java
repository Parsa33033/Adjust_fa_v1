package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Tutorial;
import com.adjust.api.repository.TutorialRepository;
import com.adjust.api.service.TutorialService;
import com.adjust.api.service.dto.TutorialDTO;
import com.adjust.api.service.mapper.TutorialMapper;

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
 * Integration tests for the {@link TutorialResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TutorialResourceIT {

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

    private static final Long DEFAULT_ADJUST_TUTORIAL_ID = 1L;
    private static final Long UPDATED_ADJUST_TUTORIAL_ID = 2L;

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private TutorialMapper tutorialMapper;

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTutorialMockMvc;

    private Tutorial tutorial;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tutorial createEntity(EntityManager em) {
        Tutorial tutorial = new Tutorial()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .text(DEFAULT_TEXT)
            .thumbnail(DEFAULT_THUMBNAIL)
            .thumbnailContentType(DEFAULT_THUMBNAIL_CONTENT_TYPE)
            .tokenPrice(DEFAULT_TOKEN_PRICE)
            .adjustTutorialId(DEFAULT_ADJUST_TUTORIAL_ID);
        return tutorial;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tutorial createUpdatedEntity(EntityManager em) {
        Tutorial tutorial = new Tutorial()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .thumbnail(UPDATED_THUMBNAIL)
            .thumbnailContentType(UPDATED_THUMBNAIL_CONTENT_TYPE)
            .tokenPrice(UPDATED_TOKEN_PRICE)
            .adjustTutorialId(UPDATED_ADJUST_TUTORIAL_ID);
        return tutorial;
    }

    @BeforeEach
    public void initTest() {
        tutorial = createEntity(em);
    }

    @Test
    @Transactional
    public void createTutorial() throws Exception {
        int databaseSizeBeforeCreate = tutorialRepository.findAll().size();
        // Create the Tutorial
        TutorialDTO tutorialDTO = tutorialMapper.toDto(tutorial);
        restTutorialMockMvc.perform(post("/api/tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialDTO)))
            .andExpect(status().isCreated());

        // Validate the Tutorial in the database
        List<Tutorial> tutorialList = tutorialRepository.findAll();
        assertThat(tutorialList).hasSize(databaseSizeBeforeCreate + 1);
        Tutorial testTutorial = tutorialList.get(tutorialList.size() - 1);
        assertThat(testTutorial.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTutorial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTutorial.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testTutorial.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testTutorial.getThumbnailContentType()).isEqualTo(DEFAULT_THUMBNAIL_CONTENT_TYPE);
        assertThat(testTutorial.getTokenPrice()).isEqualTo(DEFAULT_TOKEN_PRICE);
        assertThat(testTutorial.getAdjustTutorialId()).isEqualTo(DEFAULT_ADJUST_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void createTutorialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tutorialRepository.findAll().size();

        // Create the Tutorial with an existing ID
        tutorial.setId(1L);
        TutorialDTO tutorialDTO = tutorialMapper.toDto(tutorial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTutorialMockMvc.perform(post("/api/tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tutorial in the database
        List<Tutorial> tutorialList = tutorialRepository.findAll();
        assertThat(tutorialList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTutorials() throws Exception {
        // Initialize the database
        tutorialRepository.saveAndFlush(tutorial);

        // Get all the tutorialList
        restTutorialMockMvc.perform(get("/api/tutorials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tutorial.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].thumbnailContentType").value(hasItem(DEFAULT_THUMBNAIL_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].thumbnail").value(hasItem(Base64Utils.encodeToString(DEFAULT_THUMBNAIL))))
            .andExpect(jsonPath("$.[*].tokenPrice").value(hasItem(DEFAULT_TOKEN_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustTutorialId").value(hasItem(DEFAULT_ADJUST_TUTORIAL_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getTutorial() throws Exception {
        // Initialize the database
        tutorialRepository.saveAndFlush(tutorial);

        // Get the tutorial
        restTutorialMockMvc.perform(get("/api/tutorials/{id}", tutorial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tutorial.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.thumbnailContentType").value(DEFAULT_THUMBNAIL_CONTENT_TYPE))
            .andExpect(jsonPath("$.thumbnail").value(Base64Utils.encodeToString(DEFAULT_THUMBNAIL)))
            .andExpect(jsonPath("$.tokenPrice").value(DEFAULT_TOKEN_PRICE.doubleValue()))
            .andExpect(jsonPath("$.adjustTutorialId").value(DEFAULT_ADJUST_TUTORIAL_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTutorial() throws Exception {
        // Get the tutorial
        restTutorialMockMvc.perform(get("/api/tutorials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTutorial() throws Exception {
        // Initialize the database
        tutorialRepository.saveAndFlush(tutorial);

        int databaseSizeBeforeUpdate = tutorialRepository.findAll().size();

        // Update the tutorial
        Tutorial updatedTutorial = tutorialRepository.findById(tutorial.getId()).get();
        // Disconnect from session so that the updates on updatedTutorial are not directly saved in db
        em.detach(updatedTutorial);
        updatedTutorial
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .thumbnail(UPDATED_THUMBNAIL)
            .thumbnailContentType(UPDATED_THUMBNAIL_CONTENT_TYPE)
            .tokenPrice(UPDATED_TOKEN_PRICE)
            .adjustTutorialId(UPDATED_ADJUST_TUTORIAL_ID);
        TutorialDTO tutorialDTO = tutorialMapper.toDto(updatedTutorial);

        restTutorialMockMvc.perform(put("/api/tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialDTO)))
            .andExpect(status().isOk());

        // Validate the Tutorial in the database
        List<Tutorial> tutorialList = tutorialRepository.findAll();
        assertThat(tutorialList).hasSize(databaseSizeBeforeUpdate);
        Tutorial testTutorial = tutorialList.get(tutorialList.size() - 1);
        assertThat(testTutorial.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTutorial.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTutorial.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testTutorial.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testTutorial.getThumbnailContentType()).isEqualTo(UPDATED_THUMBNAIL_CONTENT_TYPE);
        assertThat(testTutorial.getTokenPrice()).isEqualTo(UPDATED_TOKEN_PRICE);
        assertThat(testTutorial.getAdjustTutorialId()).isEqualTo(UPDATED_ADJUST_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingTutorial() throws Exception {
        int databaseSizeBeforeUpdate = tutorialRepository.findAll().size();

        // Create the Tutorial
        TutorialDTO tutorialDTO = tutorialMapper.toDto(tutorial);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTutorialMockMvc.perform(put("/api/tutorials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tutorial in the database
        List<Tutorial> tutorialList = tutorialRepository.findAll();
        assertThat(tutorialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTutorial() throws Exception {
        // Initialize the database
        tutorialRepository.saveAndFlush(tutorial);

        int databaseSizeBeforeDelete = tutorialRepository.findAll().size();

        // Delete the tutorial
        restTutorialMockMvc.perform(delete("/api/tutorials/{id}", tutorial.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tutorial> tutorialList = tutorialRepository.findAll();
        assertThat(tutorialList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
