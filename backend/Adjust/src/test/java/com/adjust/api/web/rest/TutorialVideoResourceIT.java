package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.TutorialVideo;
import com.adjust.api.repository.TutorialVideoRepository;
import com.adjust.api.service.TutorialVideoService;
import com.adjust.api.service.dto.TutorialVideoDTO;
import com.adjust.api.service.mapper.TutorialVideoMapper;

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
 * Integration tests for the {@link TutorialVideoResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TutorialVideoResourceIT {

    private static final Long DEFAULT_ADJUST_TUTORIAL_VIDEO_ID = 1L;
    private static final Long UPDATED_ADJUST_TUTORIAL_VIDEO_ID = 2L;

    private static final byte[] DEFAULT_CONTENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONTENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONTENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONTENT_CONTENT_TYPE = "image/png";

    @Autowired
    private TutorialVideoRepository tutorialVideoRepository;

    @Autowired
    private TutorialVideoMapper tutorialVideoMapper;

    @Autowired
    private TutorialVideoService tutorialVideoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTutorialVideoMockMvc;

    private TutorialVideo tutorialVideo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TutorialVideo createEntity(EntityManager em) {
        TutorialVideo tutorialVideo = new TutorialVideo()
            .adjustTutorialVideoId(DEFAULT_ADJUST_TUTORIAL_VIDEO_ID)
            .content(DEFAULT_CONTENT)
            .contentContentType(DEFAULT_CONTENT_CONTENT_TYPE);
        return tutorialVideo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TutorialVideo createUpdatedEntity(EntityManager em) {
        TutorialVideo tutorialVideo = new TutorialVideo()
            .adjustTutorialVideoId(UPDATED_ADJUST_TUTORIAL_VIDEO_ID)
            .content(UPDATED_CONTENT)
            .contentContentType(UPDATED_CONTENT_CONTENT_TYPE);
        return tutorialVideo;
    }

    @BeforeEach
    public void initTest() {
        tutorialVideo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTutorialVideo() throws Exception {
        int databaseSizeBeforeCreate = tutorialVideoRepository.findAll().size();
        // Create the TutorialVideo
        TutorialVideoDTO tutorialVideoDTO = tutorialVideoMapper.toDto(tutorialVideo);
        restTutorialVideoMockMvc.perform(post("/api/tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialVideoDTO)))
            .andExpect(status().isCreated());

        // Validate the TutorialVideo in the database
        List<TutorialVideo> tutorialVideoList = tutorialVideoRepository.findAll();
        assertThat(tutorialVideoList).hasSize(databaseSizeBeforeCreate + 1);
        TutorialVideo testTutorialVideo = tutorialVideoList.get(tutorialVideoList.size() - 1);
        assertThat(testTutorialVideo.getAdjustTutorialVideoId()).isEqualTo(DEFAULT_ADJUST_TUTORIAL_VIDEO_ID);
        assertThat(testTutorialVideo.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testTutorialVideo.getContentContentType()).isEqualTo(DEFAULT_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createTutorialVideoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tutorialVideoRepository.findAll().size();

        // Create the TutorialVideo with an existing ID
        tutorialVideo.setId(1L);
        TutorialVideoDTO tutorialVideoDTO = tutorialVideoMapper.toDto(tutorialVideo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTutorialVideoMockMvc.perform(post("/api/tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialVideoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TutorialVideo in the database
        List<TutorialVideo> tutorialVideoList = tutorialVideoRepository.findAll();
        assertThat(tutorialVideoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTutorialVideos() throws Exception {
        // Initialize the database
        tutorialVideoRepository.saveAndFlush(tutorialVideo);

        // Get all the tutorialVideoList
        restTutorialVideoMockMvc.perform(get("/api/tutorial-videos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tutorialVideo.getId().intValue())))
            .andExpect(jsonPath("$.[*].adjustTutorialVideoId").value(hasItem(DEFAULT_ADJUST_TUTORIAL_VIDEO_ID.intValue())))
            .andExpect(jsonPath("$.[*].contentContentType").value(hasItem(DEFAULT_CONTENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTENT))));
    }
    
    @Test
    @Transactional
    public void getTutorialVideo() throws Exception {
        // Initialize the database
        tutorialVideoRepository.saveAndFlush(tutorialVideo);

        // Get the tutorialVideo
        restTutorialVideoMockMvc.perform(get("/api/tutorial-videos/{id}", tutorialVideo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tutorialVideo.getId().intValue()))
            .andExpect(jsonPath("$.adjustTutorialVideoId").value(DEFAULT_ADJUST_TUTORIAL_VIDEO_ID.intValue()))
            .andExpect(jsonPath("$.contentContentType").value(DEFAULT_CONTENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.content").value(Base64Utils.encodeToString(DEFAULT_CONTENT)));
    }
    @Test
    @Transactional
    public void getNonExistingTutorialVideo() throws Exception {
        // Get the tutorialVideo
        restTutorialVideoMockMvc.perform(get("/api/tutorial-videos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTutorialVideo() throws Exception {
        // Initialize the database
        tutorialVideoRepository.saveAndFlush(tutorialVideo);

        int databaseSizeBeforeUpdate = tutorialVideoRepository.findAll().size();

        // Update the tutorialVideo
        TutorialVideo updatedTutorialVideo = tutorialVideoRepository.findById(tutorialVideo.getId()).get();
        // Disconnect from session so that the updates on updatedTutorialVideo are not directly saved in db
        em.detach(updatedTutorialVideo);
        updatedTutorialVideo
            .adjustTutorialVideoId(UPDATED_ADJUST_TUTORIAL_VIDEO_ID)
            .content(UPDATED_CONTENT)
            .contentContentType(UPDATED_CONTENT_CONTENT_TYPE);
        TutorialVideoDTO tutorialVideoDTO = tutorialVideoMapper.toDto(updatedTutorialVideo);

        restTutorialVideoMockMvc.perform(put("/api/tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialVideoDTO)))
            .andExpect(status().isOk());

        // Validate the TutorialVideo in the database
        List<TutorialVideo> tutorialVideoList = tutorialVideoRepository.findAll();
        assertThat(tutorialVideoList).hasSize(databaseSizeBeforeUpdate);
        TutorialVideo testTutorialVideo = tutorialVideoList.get(tutorialVideoList.size() - 1);
        assertThat(testTutorialVideo.getAdjustTutorialVideoId()).isEqualTo(UPDATED_ADJUST_TUTORIAL_VIDEO_ID);
        assertThat(testTutorialVideo.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testTutorialVideo.getContentContentType()).isEqualTo(UPDATED_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTutorialVideo() throws Exception {
        int databaseSizeBeforeUpdate = tutorialVideoRepository.findAll().size();

        // Create the TutorialVideo
        TutorialVideoDTO tutorialVideoDTO = tutorialVideoMapper.toDto(tutorialVideo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTutorialVideoMockMvc.perform(put("/api/tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorialVideoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TutorialVideo in the database
        List<TutorialVideo> tutorialVideoList = tutorialVideoRepository.findAll();
        assertThat(tutorialVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTutorialVideo() throws Exception {
        // Initialize the database
        tutorialVideoRepository.saveAndFlush(tutorialVideo);

        int databaseSizeBeforeDelete = tutorialVideoRepository.findAll().size();

        // Delete the tutorialVideo
        restTutorialVideoMockMvc.perform(delete("/api/tutorial-videos/{id}", tutorialVideo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TutorialVideo> tutorialVideoList = tutorialVideoRepository.findAll();
        assertThat(tutorialVideoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
